package com.example.smsencryptsync.worker;

/**
 * 短信同步工作任务
 * 负责上传未同步的短信，并下载服务器上的新短信
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0011\u0010\u0007\u001a\u00020\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ9\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J?\u0010\u0016\u001a\u00020\u000b2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001a\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001c"}, d2 = {"Lcom/example/smsencryptsync/worker/SyncSmsWorker;", "Landroidx/work/CoroutineWorker;", "context", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downloadSms", "", "sinceTimestamp", "", "apiKey", "", "secretKey", "Ljavax/crypto/SecretKey;", "deviceId", "smsDao", "Lcom/example/smsencryptsync/data/db/SmsDao;", "(JLjava/lang/String;Ljavax/crypto/SecretKey;Ljava/lang/String;Lcom/example/smsencryptsync/data/db/SmsDao;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadSms", "smsList", "", "Lcom/example/smsencryptsync/data/model/DecryptedSms;", "(Ljava/util/List;Ljava/lang/String;Ljavax/crypto/SecretKey;Ljava/lang/String;Lcom/example/smsencryptsync/data/db/SmsDao;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public final class SyncSmsWorker extends androidx.work.CoroutineWorker {
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "SyncSmsWorker";
    @org.jetbrains.annotations.NotNull
    public static final com.example.smsencryptsync.worker.SyncSmsWorker.Companion Companion = null;
    
    public SyncSmsWorker(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    androidx.work.WorkerParameters workerParams) {
        super(null, null);
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object doWork(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> $completion) {
        return null;
    }
    
    /**
     * 上传短信到服务器
     */
    private final java.lang.Object uploadSms(java.util.List<com.example.smsencryptsync.data.model.DecryptedSms> smsList, java.lang.String apiKey, javax.crypto.SecretKey secretKey, java.lang.String deviceId, com.example.smsencryptsync.data.db.SmsDao smsDao, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * 从服务器下载新短信
     */
    private final java.lang.Object downloadSms(long sinceTimestamp, java.lang.String apiKey, javax.crypto.SecretKey secretKey, java.lang.String deviceId, com.example.smsencryptsync.data.db.SmsDao smsDao, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/example/smsencryptsync/worker/SyncSmsWorker$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}