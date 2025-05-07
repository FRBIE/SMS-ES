package com.example.smsencryptsync.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.smsencryptsync.data.model.DecryptedSms

/**
 * Room数据库类，用于管理本地短信存储
 */
@Database(entities = [DecryptedSms::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    
    /**
     * 获取短信DAO
     */
    abstract fun smsDao(): SmsDao
    
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        /**
         * 获取数据库实例（单例模式）
         */
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "sms_encrypt_sync_db"
                )
                .fallbackToDestructiveMigration() // 简化版本，如果数据库版本不兼容则重建
                .build()
                
                INSTANCE = instance
                instance
            }
        }
    }
}