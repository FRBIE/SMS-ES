package com.example.smsencryptsync.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * 加密短信上传请求模型
 */
data class EncryptedSmsRequest(
    @SerializedName("sender")
    val sender: String,
    
    @SerializedName("timestamp")
    val timestamp: Long,
    
    @SerializedName("encryptedContent")
    val encryptedContent: String,
    
    @SerializedName("iv")
    val iv: String,
    
    @SerializedName("deviceId")
    val deviceId: String
)

/**
 * 加密短信下载响应模型
 */
data class EncryptedSmsResponse(
    @SerializedName("id")
    val id: String,
    
    @SerializedName("sender")
    val sender: String,
    
    @SerializedName("timestamp")
    val timestamp: Long,
    
    @SerializedName("encryptedContent")
    val encryptedContent: String,
    
    @SerializedName("iv")
    val iv: String,
    
    @SerializedName("deviceId")
    val deviceId: String
)

/**
 * Room数据库的解密短信实体
 */
@Entity(tableName = "decrypted_sms")
data class DecryptedSms(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    val sender: String,
    
    val body: String,
    
    val timestamp: Long,
    
    val serverMsgId: String? = null,
    
    val isSyncedUp: Boolean = false,
    
    val isSyncedDown: Boolean = false,
    
    val deviceId: String
)