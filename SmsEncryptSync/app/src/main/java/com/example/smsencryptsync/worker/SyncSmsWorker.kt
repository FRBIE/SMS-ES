package com.example.smsencryptsync.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.smsencryptsync.data.api.RetrofitClient
import com.example.smsencryptsync.data.db.AppDatabase
import com.example.smsencryptsync.data.model.DecryptedSms
import com.example.smsencryptsync.data.model.EncryptedSmsRequest
import com.example.smsencryptsync.data.pref.ConfigManager
import com.example.smsencryptsync.util.EncryptionHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.crypto.SecretKey

/**
 * 短信同步工作任务
 * 负责上传未同步的短信，并下载服务器上的新短信
 */
class SyncSmsWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    companion object {
        private const val TAG = "SyncSmsWorker"
    }
    
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            Log.d(TAG, "开始同步短信...")
            
            // 获取配置
            val configManager = ConfigManager.getInstance(applicationContext)
            if (!configManager.isConfigured()) {
                Log.e(TAG, "应用未配置，无法同步")
                return@withContext Result.failure()
            }
            
            // 获取必要的配置信息
            val apiKey = configManager.getApiKey()
            val encryptionPassword = configManager.getEncryptionPassword()
            val salt = configManager.getSalt()
            val deviceId = configManager.getDeviceId()
            
            // 派生加密密钥
            val secretKey = EncryptionHelper.deriveKey(encryptionPassword, salt)
            
            // 获取数据库访问对象
            val smsDao = AppDatabase.getInstance(applicationContext).smsDao()
            
            // 1. 上传未同步的短信
            val unsyncedSmsList = smsDao.getUnsyncedSmsList()
            if (unsyncedSmsList.isNotEmpty()) {
                Log.d(TAG, "发现${unsyncedSmsList.size}条未同步的短信")
                uploadSms(unsyncedSmsList, apiKey, secretKey, deviceId, smsDao)
            } else {
                Log.d(TAG, "没有未同步的短信")
            }
            
            // 2. 下载服务器上的新短信
            val latestTimestamp = smsDao.getLatestSmsTimestamp() ?: 0L
            downloadSms(latestTimestamp, apiKey, secretKey, deviceId, smsDao)
            
            Log.d(TAG, "短信同步完成")
            Result.success()
        } catch (e: Exception) {
            Log.e(TAG, "同步过程中出现异常: ${e.message}", e)
            Result.failure()
        }
    }
    
    /**
     * 上传短信到服务器
     */
    private suspend fun uploadSms(
        smsList: List<DecryptedSms>,
        apiKey: String,
        secretKey: SecretKey,
        deviceId: String,
        smsDao: com.example.smsencryptsync.data.db.SmsDao
    ) {
        val apiService = RetrofitClient.getApiService(ConfigManager.getInstance(applicationContext))
        
        for (sms in smsList) {
            try {
                // 加密短信内容
                val (encryptedContent, iv) = EncryptionHelper.encrypt(sms.body, secretKey)
                
                // 创建上传请求
                val request = EncryptedSmsRequest(
                    sender = sms.sender,
                    timestamp = sms.timestamp,
                    encryptedContent = encryptedContent,
                    iv = iv,
                    deviceId = deviceId
                )
                
                // 发送请求
                val response = apiService.uploadSms("Bearer $apiKey", request)
                
                // 处理响应
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    val serverId = responseBody?.get("id")
                    
                    if (serverId != null) {
                        // 更新短信同步状态
                        smsDao.updateSmsUploadStatus(sms.id, serverId)
                        Log.d(TAG, "短信上传成功，ID: ${sms.id}, 服务器ID: $serverId")
                    } else {
                        Log.e(TAG, "服务器响应中没有ID")
                    }
                } else {
                    Log.e(TAG, "上传短信失败: ${response.code()}, ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e(TAG, "上传短信时发生异常: ${e.message}", e)
            }
        }
    }
    
    /**
     * 从服务器下载新短信
     */
    private suspend fun downloadSms(
        sinceTimestamp: Long,
        apiKey: String,
        secretKey: SecretKey,
        deviceId: String,
        smsDao: com.example.smsencryptsync.data.db.SmsDao
    ) {
        try {
            val apiService = RetrofitClient.getApiService(ConfigManager.getInstance(applicationContext))
            
            Log.d(TAG, "开始从服务器下载短信，时间戳：$sinceTimestamp，设备ID：$deviceId")
            
            // 发送请求获取新短信
            val response = apiService.downloadSms(
                "Bearer $apiKey",
                sinceTimestamp,
                deviceId
            )
            
            // 处理响应
            if (response.isSuccessful) {
                val encryptedSmsList = response.body()
                
                Log.d(TAG, "服务器响应成功，状态码：${response.code()}，收到数据：${encryptedSmsList?.size ?: 0}条")
                
                if (encryptedSmsList != null && encryptedSmsList.isNotEmpty()) {
                    Log.d(TAG, "从服务器获取到${encryptedSmsList.size}条新短信")
                    
                    // 记录每条消息的基本信息
                    encryptedSmsList.forEachIndexed { index, sms ->
                        Log.d(TAG, "短信[$index] - ID: ${sms.id}, 发送者: ${sms.sender}")
                    }
                    
                    // 解密并保存到本地数据库
                    val decryptedSmsList = encryptedSmsList.map { encryptedSms ->
                        try {
                            // 解密短信内容
                            val decryptedBody = EncryptionHelper.decrypt(
                                encryptedSms.encryptedContent,
                                encryptedSms.iv,
                                secretKey
                            )
                            
                            Log.d(TAG, "成功解密短信，ID: ${encryptedSms.id}")
                            
                            // 创建本地短信对象
                            DecryptedSms(
                                sender = encryptedSms.sender,
                                body = decryptedBody,
                                timestamp = encryptedSms.timestamp,
                                serverMsgId = encryptedSms.id,
                                isSyncedUp = true,
                                isSyncedDown = true,
                                deviceId = encryptedSms.deviceId
                            )
                        } catch (e: Exception) {
                            Log.e(TAG, "解密短信失败: ${e.message}", e)
                            null
                        }
                    }.filterNotNull()
                    
                    // 保存到本地数据库
                    if (decryptedSmsList.isNotEmpty()) {
                        smsDao.insertAll(decryptedSmsList)
                        Log.d(TAG, "成功保存${decryptedSmsList.size}条短信到数据库")
                    }
                } else {
                    Log.d(TAG, "服务器上没有新短信")
                }
            } else {
                Log.e(TAG, "下载短信失败: ${response.code()}, ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            Log.e(TAG, "下载短信时发生异常: ${e.message}", e)
        }
    }
}