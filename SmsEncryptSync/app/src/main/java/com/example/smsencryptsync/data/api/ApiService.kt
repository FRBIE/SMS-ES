package com.example.smsencryptsync.data.api

import com.example.smsencryptsync.data.model.EncryptedSmsRequest
import com.example.smsencryptsync.data.model.EncryptedSmsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * 网络API服务接口
 */
interface ApiService {
    /**
     * 上传加密短信
     * @param apiKey 认证密钥
     * @param smsData 加密短信数据
     */
    @POST("sms/upload")
    suspend fun uploadSms(
        @Header("Authorization") apiKey: String,
        @Body smsData: EncryptedSmsRequest
    ): Response<Map<String, String>> // 返回包含id的Map
    
    /**
     * 下载加密短信列表
     * @param apiKey 认证密钥
     * @param sinceTimestamp 可选，只返回指定时间戳之后的短信
     */
    @GET("sms/download")
    suspend fun downloadSms(
        @Header("Authorization") apiKey: String,
        @Query("since_timestamp") sinceTimestamp: Long? = null,
        @Query("device_id") deviceId: String
    ): Response<List<EncryptedSmsResponse>>
}