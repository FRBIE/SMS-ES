package com.example.smsencryptsync.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.smsencryptsync.data.api.RetrofitClient
import com.example.smsencryptsync.data.db.AppDatabase
import com.example.smsencryptsync.data.model.EncryptedSmsRequest
import com.example.smsencryptsync.data.pref.ConfigManager
import com.example.smsencryptsync.util.EncryptionHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 负责加密并上传单条短信的工作任务
 */
class UploadSmsWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    companion object {
        private const val TAG = "UploadSmsWorker"
        
        // 输入数据键名
        const val KEY_SMS_ID = "sms_id"
        const val KEY_SMS_SENDER = "sms_sender"
        const val KEY_SMS_BODY = "sms_body"
        const val KEY_SMS_TIMESTAMP = "sms_timestamp"
        
        // 输出数据键名
        const val KEY_UPLOAD_RESULT = "upload_result"
        const val KEY_SERVER_ID = "server_id"
    }

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        Log.d(TAG, "开始执行短信上传工作任务...")
        
        try {
            // 从输入数据中获取短信信息
            val smsId = inputData.getLong(KEY_SMS_ID, -1)
            val smsSender = inputData.getString(KEY_SMS_SENDER)
            val smsBody = inputData.getString(KEY_SMS_BODY)
            val smsTimestamp = inputData.getLong(KEY_SMS_TIMESTAMP, System.currentTimeMillis())
            
            Log.d(TAG, "获取到短信数据 - ID: $smsId, 发送者: $smsSender, 时间戳: $smsTimestamp")
            
            if (smsSender == null || smsBody == null) {
                Log.e(TAG, "短信发送者或内容为空，无法上传")
                return@withContext Result.failure()
            }
            
            // 检查配置
            val configManager = ConfigManager.getInstance(applicationContext)
            if (!configManager.isConfigured()) {
                Log.e(TAG, "应用未配置，无法上传短信")
                return@withContext Result.failure()
            }
            
            // 获取必要的配置信息
            val apiKey = configManager.getApiKey()
            val serverUrl = configManager.getServerUrl()
            val encryptionPassword = configManager.getEncryptionPassword()
            val salt = configManager.getSalt()
            val deviceId = configManager.getDeviceId()
            
            Log.d(TAG, "配置校验通过，服务器URL: $serverUrl, 设备ID: $deviceId")
            
            // 派生加密密钥
            val secretKey = EncryptionHelper.deriveKey(encryptionPassword, salt)
            Log.d(TAG, "已派生加密密钥")
            
            // 加密短信内容
            val (encryptedContent, iv) = EncryptionHelper.encrypt(smsBody, secretKey)
            Log.d(TAG, "短信已成功加密")
            
            // 构造上传请求
            val request = EncryptedSmsRequest(
                sender = smsSender,
                timestamp = smsTimestamp,
                encryptedContent = encryptedContent,
                iv = iv,
                deviceId = deviceId
            )
            
            // 发送请求
            Log.d(TAG, "开始发送上传请求到服务器: $serverUrl")
            val apiService = RetrofitClient.getApiService(configManager)
            val response = apiService.uploadSms("Bearer $apiKey", request)
            
            // 处理响应
            return@withContext if (response.isSuccessful) {
                val responseBody = response.body()
                Log.d(TAG, "服务器响应成功: ${response.code()}, 响应体: $responseBody")
                
                val serverId = responseBody?.get("id")
                
                if (serverId != null) {
                    // 如果上传成功并获得了服务器ID，更新数据库中的同步状态
                    if (smsId != -1L) {
                        val smsDao = AppDatabase.getInstance(applicationContext).smsDao()
                        smsDao.updateSmsUploadStatus(smsId, serverId)
                        Log.d(TAG, "短信同步状态已更新，本地ID: $smsId, 服务器ID: $serverId")
                    }
                    
                    Log.d(TAG, "短信上传成功，ID: $smsId, 服务器ID: $serverId")
                    Result.success(workDataOf(
                        KEY_UPLOAD_RESULT to true,
                        KEY_SERVER_ID to serverId
                    ))
                } else {
                    Log.e(TAG, "服务器响应中没有ID")
                    Result.failure()
                }
            } else {
                Log.e(TAG, "上传短信失败: ${response.code()}, ${response.errorBody()?.string()}")
                Result.failure()
            }
        } catch (e: Exception) {
            Log.e(TAG, "上传短信时发生异常: ${e.message}")
            e.printStackTrace()
            Result.failure()
        }
    }
}