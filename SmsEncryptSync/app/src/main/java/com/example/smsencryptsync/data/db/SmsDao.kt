package com.example.smsencryptsync.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.smsencryptsync.data.model.DecryptedSms

/**
 * 数据库访问对象(DAO)，用于操作短信数据表
 */
@Dao
interface SmsDao {
    /**
     * 插入新短信
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(sms: DecryptedSms): Long
    
    /**
     * 批量插入短信
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(smsList: List<DecryptedSms>)
    
    /**
     * 更新短信
     */
    @Update
    suspend fun update(sms: DecryptedSms)
    
    /**
     * 获取所有短信，按时间戳降序排列
     */
    @Query("SELECT * FROM decrypted_sms ORDER BY timestamp DESC")
    fun getAllSms(): LiveData<List<DecryptedSms>>
    
    /**
     * 根据ID获取短信
     */
    @Query("SELECT * FROM decrypted_sms WHERE id = :id")
    suspend fun getSmsById(id: Long): DecryptedSms?
    
    /**
     * 根据服务器ID获取短信
     */
    @Query("SELECT * FROM decrypted_sms WHERE serverMsgId = :serverMsgId")
    suspend fun getSmsByServerId(serverMsgId: String): DecryptedSms?
    
    /**
     * 获取所有未上传的短信
     */
    @Query("SELECT * FROM decrypted_sms WHERE isSyncedUp = 0")
    suspend fun getUnsyncedSmsList(): List<DecryptedSms>
    
    /**
     * 更新短信的上传状态
     */
    @Query("UPDATE decrypted_sms SET isSyncedUp = 1, serverMsgId = :serverMsgId WHERE id = :id")
    suspend fun updateSmsUploadStatus(id: Long, serverMsgId: String)
    
    /**
     * 获取最新短信的时间戳
     */
    @Query("SELECT MAX(timestamp) FROM decrypted_sms")
    suspend fun getLatestSmsTimestamp(): Long?
    
    /**
     * 删除短信
     */
    @Query("DELETE FROM decrypted_sms WHERE id = :id")
    suspend fun deleteSms(id: Long)
    
    /**
     * 删除所有短信
     */
    @Query("DELETE FROM decrypted_sms")
    suspend fun deleteAllSms()
}