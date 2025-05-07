package com.example.smsencryptsync.worker;

/**
 * 负责加密并上传单条短信的工作任务
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0011\u0010\u0007\u001a\u00020\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000b"}, d2 = {"Lcom/example/smsencryptsync/worker/UploadSmsWorker;", "Landroidx/work/CoroutineWorker;", "context", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public final class UploadSmsWorker extends androidx.work.CoroutineWorker {
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "UploadSmsWorker";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_SMS_ID = "sms_id";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_SMS_SENDER = "sms_sender";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_SMS_BODY = "sms_body";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_SMS_TIMESTAMP = "sms_timestamp";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_UPLOAD_RESULT = "upload_result";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_SERVER_ID = "server_id";
    @org.jetbrains.annotations.NotNull
    public static final com.example.smsencryptsync.worker.UploadSmsWorker.Companion Companion = null;
    
    public UploadSmsWorker(@org.jetbrains.annotations.NotNull
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/example/smsencryptsync/worker/UploadSmsWorker$Companion;", "", "()V", "KEY_SERVER_ID", "", "KEY_SMS_BODY", "KEY_SMS_ID", "KEY_SMS_SENDER", "KEY_SMS_TIMESTAMP", "KEY_UPLOAD_RESULT", "TAG", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}