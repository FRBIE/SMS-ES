package com.example.smsencryptsync.util;

/**
 * 加密/解密工具类，提供短信内容的加密和解密功能
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lcom/example/smsencryptsync/util/EncryptionHelper;", "", "()V", "Companion", "app_debug"})
public final class EncryptionHelper {
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String ALGORITHM = "AES/GCM/NoPadding";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_ALGORITHM = "AES";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_DERIVATION_ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final int GCM_TAG_LENGTH = 128;
    private static final int ITERATION_COUNT = 10000;
    private static final int KEY_LENGTH = 256;
    @org.jetbrains.annotations.NotNull
    public static final com.example.smsencryptsync.util.EncryptionHelper.Companion Companion = null;
    
    public EncryptionHelper() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0013J\"\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00152\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0017\u001a\u00020\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/example/smsencryptsync/util/EncryptionHelper$Companion;", "", "()V", "ALGORITHM", "", "GCM_TAG_LENGTH", "", "ITERATION_COUNT", "KEY_ALGORITHM", "KEY_DERIVATION_ALGORITHM", "KEY_LENGTH", "decrypt", "cipherTextBase64", "ivBase64", "secretKey", "Ljavax/crypto/SecretKey;", "deriveKey", "password", "salt", "", "encrypt", "Lkotlin/Pair;", "plainText", "generateSalt", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * 从用户密码生成加密密钥
         * @param password 用户提供的密码
         * @param salt 用于派生密钥的盐值
         * @return 加密用的SecretKey
         */
        @org.jetbrains.annotations.NotNull
        public final javax.crypto.SecretKey deriveKey(@org.jetbrains.annotations.NotNull
        java.lang.String password, @org.jetbrains.annotations.NotNull
        byte[] salt) {
            return null;
        }
        
        /**
         * 加密明文内容
         * @param plainText 要加密的明文字符串
         * @param secretKey 加密密钥
         * @return Pair<加密后的Base64字符串, IV的Base64字符串>
         */
        @org.jetbrains.annotations.NotNull
        public final kotlin.Pair<java.lang.String, java.lang.String> encrypt(@org.jetbrains.annotations.NotNull
        java.lang.String plainText, @org.jetbrains.annotations.NotNull
        javax.crypto.SecretKey secretKey) {
            return null;
        }
        
        /**
         * 解密加密内容
         * @param cipherTextBase64 Base64编码的加密内容
         * @param ivBase64 Base64编码的IV
         * @param secretKey 解密密钥
         * @return 解密后的明文字符串
         */
        @org.jetbrains.annotations.NotNull
        public final java.lang.String decrypt(@org.jetbrains.annotations.NotNull
        java.lang.String cipherTextBase64, @org.jetbrains.annotations.NotNull
        java.lang.String ivBase64, @org.jetbrains.annotations.NotNull
        javax.crypto.SecretKey secretKey) {
            return null;
        }
        
        /**
         * 生成随机盐值
         * @return 16字节的随机盐值
         */
        @org.jetbrains.annotations.NotNull
        public final byte[] generateSalt() {
            return null;
        }
    }
}