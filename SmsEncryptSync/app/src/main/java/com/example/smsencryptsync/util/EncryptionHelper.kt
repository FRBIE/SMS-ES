package com.example.smsencryptsync.util

import android.util.Base64
import java.security.GeneralSecurityException
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

/**
 * 加密/解密工具类，提供短信内容的加密和解密功能
 */
class EncryptionHelper {
    companion object {
        // AES-GCM规范常量
        private const val ALGORITHM = "AES/GCM/NoPadding"
        private const val KEY_ALGORITHM = "AES"
        private const val KEY_DERIVATION_ALGORITHM = "PBKDF2WithHmacSHA256"
        private const val GCM_TAG_LENGTH = 128
        private const val ITERATION_COUNT = 10000
        private const val KEY_LENGTH = 256 // 密钥长度为256位

        /**
         * 从用户密码生成加密密钥
         * @param password 用户提供的密码
         * @param salt 用于派生密钥的盐值
         * @return 加密用的SecretKey
         */
        fun deriveKey(password: String, salt: ByteArray): SecretKey {
            val keySpec = PBEKeySpec(password.toCharArray(), salt, ITERATION_COUNT, KEY_LENGTH)
            val keyFactory = SecretKeyFactory.getInstance(KEY_DERIVATION_ALGORITHM)
            val keyBytes = keyFactory.generateSecret(keySpec).encoded
            return SecretKeySpec(keyBytes, KEY_ALGORITHM)
        }

        /**
         * 加密明文内容
         * @param plainText 要加密的明文字符串
         * @param secretKey 加密密钥
         * @return Pair<加密后的Base64字符串, IV的Base64字符串>
         */
        fun encrypt(plainText: String, secretKey: SecretKey): Pair<String, String> {
            try {
                // 生成随机IV
                val iv = ByteArray(12) // GCM模式下IV通常为12字节
                SecureRandom().nextBytes(iv)
                
                // 初始化加密器
                val cipher = Cipher.getInstance(ALGORITHM)
                val parameterSpec = GCMParameterSpec(GCM_TAG_LENGTH, iv)
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpec)
                
                // 加密数据
                val encryptedBytes = cipher.doFinal(plainText.toByteArray(Charsets.UTF_8))
                
                // 转换为Base64编码
                val encryptedBase64 = Base64.encodeToString(encryptedBytes, Base64.DEFAULT)
                val ivBase64 = Base64.encodeToString(iv, Base64.DEFAULT)
                
                return Pair(encryptedBase64, ivBase64)
            } catch (e: GeneralSecurityException) {
                throw RuntimeException("加密失败: ${e.message}", e)
            }
        }

        /**
         * 解密加密内容
         * @param cipherTextBase64 Base64编码的加密内容
         * @param ivBase64 Base64编码的IV
         * @param secretKey 解密密钥
         * @return 解密后的明文字符串
         */
        fun decrypt(cipherTextBase64: String, ivBase64: String, secretKey: SecretKey): String {
            try {
                // 解码Base64
                val encryptedBytes = Base64.decode(cipherTextBase64, Base64.DEFAULT)
                val iv = Base64.decode(ivBase64, Base64.DEFAULT)
                
                // 初始化解密器
                val cipher = Cipher.getInstance(ALGORITHM)
                val parameterSpec = GCMParameterSpec(GCM_TAG_LENGTH, iv)
                cipher.init(Cipher.DECRYPT_MODE, secretKey, parameterSpec)
                
                // 解密数据
                val decryptedBytes = cipher.doFinal(encryptedBytes)
                return String(decryptedBytes, Charsets.UTF_8)
            } catch (e: GeneralSecurityException) {
                throw RuntimeException("解密失败: ${e.message}", e)
            }
        }

        /**
         * 生成随机盐值
         * @return 16字节的随机盐值
         */
        fun generateSalt(): ByteArray {
            val salt = ByteArray(16)
            SecureRandom().nextBytes(salt)
            return salt
        }
    }
}