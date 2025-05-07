package com.example.smsencryptsync.data.api

import com.example.smsencryptsync.data.pref.ConfigManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Retrofit网络请求客户端
 */
class RetrofitClient private constructor() {
    companion object {
        private const val TIMEOUT = 30L
        
        @Volatile
        private var INSTANCE: ApiService? = null
        
        /**
         * 获取API服务实例
         * @param baseUrl 服务器基础URL
         */
        fun getApiService(baseUrl: String): ApiService {
            return INSTANCE ?: synchronized(this) {
                val instance = createRetrofit(baseUrl).create(ApiService::class.java)
                INSTANCE = instance
                instance
            }
        }
        
        /**
         * 根据ConfigManager中的URL创建API服务
         */
        fun getApiService(configManager: ConfigManager): ApiService {
            val baseUrl = configManager.getServerUrl()
            require(baseUrl.isNotEmpty()) { "服务器URL不能为空，请先在设置中配置" }
            return getApiService(baseUrl)
        }
        
        /**
         * 重置API服务实例（当URL更改时使用）
         */
        fun resetApiService() {
            INSTANCE = null
        }
        
        /**
         * 创建Retrofit实例
         */
        private fun createRetrofit(baseUrl: String): Retrofit {
            // 确保baseUrl是一个有效的基础URL（不包含API路径）
            // 先移除可能的尾部斜杠，然后检测是否包含API路径
            var formattedUrl = baseUrl.trimEnd('/')
            
            // 如果URL错误地包含了API路径，则移除它们
            if (formattedUrl.endsWith("/sms/upload")) {
                formattedUrl = formattedUrl.removeSuffix("/sms/upload")
            } else if (formattedUrl.contains("/sms/")) {
                // 找到最后一个/sms/的位置，并截取到该位置前
                val index = formattedUrl.lastIndexOf("/sms/")
                if (index > 0) {
                    formattedUrl = formattedUrl.substring(0, index)
                }
            }
            
            // 最后确保URL以斜杠结尾
            formattedUrl = if (formattedUrl.endsWith("/")) formattedUrl else "$formattedUrl/"
            
            // 配置OkHttp客户端
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build()
            
            // 创建Retrofit实例
            return Retrofit.Builder()
                .baseUrl(formattedUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}