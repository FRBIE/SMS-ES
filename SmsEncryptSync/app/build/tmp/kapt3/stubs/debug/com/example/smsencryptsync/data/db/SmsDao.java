package com.example.smsencryptsync.data.db;

/**
 * 数据库访问对象(DAO)，用于操作短信数据表
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\f\bg\u0018\u00002\u00020\u0001J\u0011\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nH\'J\u0013\u0010\r\u001a\u0004\u0018\u00010\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u001b\u0010\u000e\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u001b\u0010\u000f\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J\u001f\u0010\u0017\u001a\u00020\u00032\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019J\u0019\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J!\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001c\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001d"}, d2 = {"Lcom/example/smsencryptsync/data/db/SmsDao;", "", "deleteAllSms", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteSms", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllSms", "Landroidx/lifecycle/LiveData;", "", "Lcom/example/smsencryptsync/data/model/DecryptedSms;", "getLatestSmsTimestamp", "getSmsById", "getSmsByServerId", "serverMsgId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUnsyncedSmsList", "insert", "sms", "(Lcom/example/smsencryptsync/data/model/DecryptedSms;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAll", "smsList", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "updateSmsUploadStatus", "(JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao
public abstract interface SmsDao {
    
    /**
     * 插入新短信
     */
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull
    com.example.smsencryptsync.data.model.DecryptedSms sms, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    /**
     * 批量插入短信
     */
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.smsencryptsync.data.model.DecryptedSms> smsList, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * 更新短信
     */
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull
    com.example.smsencryptsync.data.model.DecryptedSms sms, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * 获取所有短信，按时间戳降序排列
     */
    @androidx.room.Query(value = "SELECT * FROM decrypted_sms ORDER BY timestamp DESC")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.util.List<com.example.smsencryptsync.data.model.DecryptedSms>> getAllSms();
    
    /**
     * 根据ID获取短信
     */
    @androidx.room.Query(value = "SELECT * FROM decrypted_sms WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getSmsById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.smsencryptsync.data.model.DecryptedSms> $completion);
    
    /**
     * 根据服务器ID获取短信
     */
    @androidx.room.Query(value = "SELECT * FROM decrypted_sms WHERE serverMsgId = :serverMsgId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getSmsByServerId(@org.jetbrains.annotations.NotNull
    java.lang.String serverMsgId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.smsencryptsync.data.model.DecryptedSms> $completion);
    
    /**
     * 获取所有未上传的短信
     */
    @androidx.room.Query(value = "SELECT * FROM decrypted_sms WHERE isSyncedUp = 0")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getUnsyncedSmsList(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.smsencryptsync.data.model.DecryptedSms>> $completion);
    
    /**
     * 更新短信的上传状态
     */
    @androidx.room.Query(value = "UPDATE decrypted_sms SET isSyncedUp = 1, serverMsgId = :serverMsgId WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateSmsUploadStatus(long id, @org.jetbrains.annotations.NotNull
    java.lang.String serverMsgId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * 获取最新短信的时间戳
     */
    @androidx.room.Query(value = "SELECT MAX(timestamp) FROM decrypted_sms")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getLatestSmsTimestamp(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    /**
     * 删除短信
     */
    @androidx.room.Query(value = "DELETE FROM decrypted_sms WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteSms(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * 删除所有短信
     */
    @androidx.room.Query(value = "DELETE FROM decrypted_sms")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteAllSms(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}