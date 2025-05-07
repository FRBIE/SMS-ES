package com.example.smsencryptsync.data.pref;

/**
 * 配置管理类，负责存储和读取用户配置
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0006\u0010\u000b\u001a\u00020\fJ\u0012\u0010\r\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000e\u001a\u00020\bH\u0002J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\bH\u0002J\u0006\u0010\u0011\u001a\u00020\bJ\u0006\u0010\u0012\u001a\u00020\bJ\u0006\u0010\u0013\u001a\u00020\bJ\b\u0010\u0014\u001a\u00020\u0015H\u0002J\u0006\u0010\u0016\u001a\u00020\nJ\u0006\u0010\u0017\u001a\u00020\bJ\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\bJ\u000e\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\bJ\u000e\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\nJ\u000e\u0010 \u001a\u00020\f2\u0006\u0010!\u001a\u00020\bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/example/smsencryptsync/data/pref/ConfigManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "sharedPreferences", "Landroid/content/SharedPreferences;", "bytesToHex", "", "bytes", "", "clearConfig", "", "decrypt", "encryptedData", "encrypt", "plainText", "getApiKey", "getDeviceId", "getEncryptionPassword", "getOrCreateSecretKey", "Ljavax/crypto/SecretKey;", "getSalt", "getServerUrl", "isConfigured", "", "saveApiKey", "apiKey", "saveEncryptionPassword", "password", "saveSalt", "salt", "saveServerUrl", "url", "Companion", "app_debug"})
public final class ConfigManager {
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String PREFS_NAME = "sms_encrypt_sync_prefs";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_SERVER_URL = "server_url";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_API_KEY = "api_key";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_ENCRYPTION_PASSWORD = "encryption_password";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_SALT = "encryption_salt";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_DEVICE_ID = "device_id";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEYSTORE_ALIAS = "sms_encrypt_sync_key";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TRANSFORMATION = "AES/GCM/NoPadding";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String IV_SEPARATOR = "]";
    @kotlin.jvm.Volatile
    @org.jetbrains.annotations.Nullable
    private static volatile com.example.smsencryptsync.data.pref.ConfigManager instance;
    @org.jetbrains.annotations.NotNull
    private final android.content.SharedPreferences sharedPreferences = null;
    @org.jetbrains.annotations.NotNull
    public static final com.example.smsencryptsync.data.pref.ConfigManager.Companion Companion = null;
    
    private ConfigManager(android.content.Context context) {
        super();
    }
    
    /**
     * 获取或创建AndroidKeyStore中的密钥
     */
    private final javax.crypto.SecretKey getOrCreateSecretKey() {
        return null;
    }
    
    /**
     * 加密字符串
     */
    private final java.lang.String encrypt(java.lang.String plainText) {
        return null;
    }
    
    /**
     * 解密字符串
     */
    private final java.lang.String decrypt(java.lang.String encryptedData) {
        return null;
    }
    
    /**
     * 保存服务器URL
     */
    public final void saveServerUrl(@org.jetbrains.annotations.NotNull
    java.lang.String url) {
    }
    
    /**
     * 获取服务器URL
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getServerUrl() {
        return null;
    }
    
    /**
     * 保存API密钥（加密存储）
     */
    public final void saveApiKey(@org.jetbrains.annotations.NotNull
    java.lang.String apiKey) {
    }
    
    /**
     * 获取API密钥（解密获取）
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getApiKey() {
        return null;
    }
    
    /**
     * 保存加密密码（加密存储）
     */
    public final void saveEncryptionPassword(@org.jetbrains.annotations.NotNull
    java.lang.String password) {
    }
    
    /**
     * 获取加密密码（解密获取）
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getEncryptionPassword() {
        return null;
    }
    
    /**
     * 保存加密的盐值
     */
    public final void saveSalt(@org.jetbrains.annotations.NotNull
    byte[] salt) {
    }
    
    /**
     * 获取加密的盐值，如果不存在则使用默认值
     * 注意：为了测试目的，使用固定盐值
     */
    @org.jetbrains.annotations.NotNull
    public final byte[] getSalt() {
        return null;
    }
    
    /**
     * 辅助方法：将字节数组转换为十六进制字符串
     */
    private final java.lang.String bytesToHex(byte[] bytes) {
        return null;
    }
    
    /**
     * 获取设备唯一ID
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getDeviceId() {
        return null;
    }
    
    /**
     * 清除配置信息
     */
    public final void clearConfig() {
    }
    
    /**
     * 检查是否已完成设置
     */
    public final boolean isConfigured() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/example/smsencryptsync/data/pref/ConfigManager$Companion;", "", "()V", "IV_SEPARATOR", "", "KEYSTORE_ALIAS", "KEY_API_KEY", "KEY_DEVICE_ID", "KEY_ENCRYPTION_PASSWORD", "KEY_SALT", "KEY_SERVER_URL", "PREFS_NAME", "TRANSFORMATION", "instance", "Lcom/example/smsencryptsync/data/pref/ConfigManager;", "getInstance", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.smsencryptsync.data.pref.ConfigManager getInstance(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
            return null;
        }
    }
}