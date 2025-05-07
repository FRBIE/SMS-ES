package com.example.smsencryptsync.data.api;

/**
 * Retrofit网络请求客户端
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lcom/example/smsencryptsync/data/api/RetrofitClient;", "", "()V", "Companion", "app_debug"})
public final class RetrofitClient {
    private static final long TIMEOUT = 30L;
    @kotlin.jvm.Volatile
    @org.jetbrains.annotations.Nullable
    private static volatile com.example.smsencryptsync.data.api.ApiService INSTANCE;
    @org.jetbrains.annotations.NotNull
    public static final com.example.smsencryptsync.data.api.RetrofitClient.Companion Companion = null;
    
    private RetrofitClient() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000e\u001a\u00020\u000fR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/example/smsencryptsync/data/api/RetrofitClient$Companion;", "", "()V", "INSTANCE", "Lcom/example/smsencryptsync/data/api/ApiService;", "TIMEOUT", "", "createRetrofit", "Lretrofit2/Retrofit;", "baseUrl", "", "getApiService", "configManager", "Lcom/example/smsencryptsync/data/pref/ConfigManager;", "resetApiService", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * 获取API服务实例
         * @param baseUrl 服务器基础URL
         */
        @org.jetbrains.annotations.NotNull
        public final com.example.smsencryptsync.data.api.ApiService getApiService(@org.jetbrains.annotations.NotNull
        java.lang.String baseUrl) {
            return null;
        }
        
        /**
         * 根据ConfigManager中的URL创建API服务
         */
        @org.jetbrains.annotations.NotNull
        public final com.example.smsencryptsync.data.api.ApiService getApiService(@org.jetbrains.annotations.NotNull
        com.example.smsencryptsync.data.pref.ConfigManager configManager) {
            return null;
        }
        
        /**
         * 重置API服务实例（当URL更改时使用）
         */
        public final void resetApiService() {
        }
        
        /**
         * 创建Retrofit实例
         */
        private final retrofit2.Retrofit createRetrofit(java.lang.String baseUrl) {
            return null;
        }
    }
}