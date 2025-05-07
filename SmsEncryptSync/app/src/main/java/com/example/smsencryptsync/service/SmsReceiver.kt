package com.example.smsencryptsync.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.util.Log
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.smsencryptsync.data.db.AppDatabase
import com.example.smsencryptsync.data.model.DecryptedSms
import com.example.smsencryptsync.data.pref.ConfigManager
import com.example.smsencryptsync.util.PermissionUtils
import com.example.smsencryptsync.worker.UploadSmsWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * 短信广播接收器，用于监听并处理新接收的短信
 */
class SmsReceiver : BroadcastReceiver() {

    private val TAG = "SmsReceiver"
    
    override fun onReceive(context: Context, intent: Intent) {
        // 检查是否有短信接收权限
        if (!PermissionUtils.hasSmsPermission(context)) {
            Log.e(TAG, "没有短信接收权限，无法处理短信")
            return
        }
        
        // 检查是否是短信接收广播
        if (intent.action != Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            return
        }
        
        // 检查配置是否完成
        val configManager = ConfigManager.getInstance(context)
        if (!configManager.isConfigured()) {
            Log.i(TAG, "应用配置未完成，跳过短信处理")
            return
        }
        
        // 解析短信内容
        val messages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
        if (messages.isEmpty()) {
            Log.w(TAG, "未能从Intent中获取短信内容")
            return
        }
        
        // 处理每条短信
        for (smsMessage in messages) {
            val sender = smsMessage.originatingAddress ?: "未知号码"
            val body = smsMessage.messageBody
            val timestamp = smsMessage.timestampMillis
            
            Log.d(TAG, "收到来自 $sender 的短信: ${body.take(10)}...")
            
            // 保存短信到本地数据库
            val deviceId = configManager.getDeviceId()
            val sms = DecryptedSms(
                sender = sender,
                body = body,
                timestamp = timestamp,
                deviceId = deviceId,
                isSyncedUp = false,
                isSyncedDown = false
            )
            
            // 使用协程异步保存
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val smsDao = AppDatabase.getInstance(context).smsDao()
                    val smsId = smsDao.insert(sms)
                    
                    // 在保存成功后，调度上传工作
                    scheduleSmsUploadWork(context, smsId, sender, body, timestamp)
                    Log.d(TAG, "短信已保存到本地数据库，ID: $smsId")
                } catch (e: Exception) {
                    Log.e(TAG, "保存短信到数据库失败: ${e.message}", e)
                }
            }
        }
    }
    
    /**
     * 调度短信上传工作
     */
    private fun scheduleSmsUploadWork(context: Context, smsId: Long, sender: String, body: String, timestamp: Long) {
        Log.d(TAG, "开始调度短信上传工作，SMS ID: $smsId, 发送者: $sender")
        
        // 创建工作请求的输入数据
        val inputData = Data.Builder()
            .putLong(UploadSmsWorker.KEY_SMS_ID, smsId)
            .putString(UploadSmsWorker.KEY_SMS_SENDER, sender)
            .putString(UploadSmsWorker.KEY_SMS_BODY, body)
            .putLong(UploadSmsWorker.KEY_SMS_TIMESTAMP, timestamp)
            .build()
            
        // 创建一次性工作请求，设置为高优先级立即执行
        val uploadWorkRequest = OneTimeWorkRequestBuilder<UploadSmsWorker>()
            .setInputData(inputData)
            .setExpedited(androidx.work.OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST) // 尝试立即执行
            .build()
            
        // 使用唯一工作名称调度工作（加上SMS ID以区分多个任务）
        WorkManager.getInstance(context).enqueueUniqueWork(
            "sms_upload_${smsId}",
            ExistingWorkPolicy.REPLACE,
            uploadWorkRequest
        )
        
        Log.d(TAG, "已调度短信上传工作，SMS ID: $smsId, 工作ID: ${uploadWorkRequest.id}")
    }
}