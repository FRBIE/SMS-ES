package com.example.smsencryptsync.data.api;

/**
 * 网络API服务接口
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J=\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0001\u0010\n\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ7\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\r0\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0011"}, d2 = {"Lcom/example/smsencryptsync/data/api/ApiService;", "", "downloadSms", "Lretrofit2/Response;", "", "Lcom/example/smsencryptsync/data/model/EncryptedSmsResponse;", "apiKey", "", "sinceTimestamp", "", "deviceId", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadSms", "", "smsData", "Lcom/example/smsencryptsync/data/model/EncryptedSmsRequest;", "(Ljava/lang/String;Lcom/example/smsencryptsync/data/model/EncryptedSmsRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface ApiService {
    
    /**
     * 上传加密短信
     * @param apiKey 认证密钥
     * @param smsData 加密短信数据
     */
    @retrofit2.http.POST(value = "sms/upload")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object uploadSms(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull
    java.lang.String apiKey, @retrofit2.http.Body
    @org.jetbrains.annotations.NotNull
    com.example.smsencryptsync.data.model.EncryptedSmsRequest smsData, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.Map<java.lang.String, java.lang.String>>> $completion);
    
    /**
     * 下载加密短信列表
     * @param apiKey 认证密钥
     * @param sinceTimestamp 可选，只返回指定时间戳之后的短信
     */
    @retrofit2.http.GET(value = "sms/download")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object downloadSms(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull
    java.lang.String apiKey, @retrofit2.http.Query(value = "since_timestamp")
    @org.jetbrains.annotations.Nullable
    java.lang.Long sinceTimestamp, @retrofit2.http.Query(value = "device_id")
    @org.jetbrains.annotations.NotNull
    java.lang.String deviceId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.example.smsencryptsync.data.model.EncryptedSmsResponse>>> $completion);
    
    /**
     * 网络API服务接口
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}