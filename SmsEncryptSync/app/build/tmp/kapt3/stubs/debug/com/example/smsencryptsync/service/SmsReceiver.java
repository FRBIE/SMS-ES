package com.example.smsencryptsync.service;

/**
 * 短信广播接收器，用于监听并处理新接收的短信
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J0\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\rH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/example/smsencryptsync/service/SmsReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "TAG", "", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "scheduleSmsUploadWork", "smsId", "", "sender", "body", "timestamp", "app_debug"})
public final class SmsReceiver extends android.content.BroadcastReceiver {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String TAG = "SmsReceiver";
    
    public SmsReceiver() {
        super();
    }
    
    @java.lang.Override
    public void onReceive(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    android.content.Intent intent) {
    }
    
    /**
     * 调度短信上传工作
     */
    private final void scheduleSmsUploadWork(android.content.Context context, long smsId, java.lang.String sender, java.lang.String body, long timestamp) {
    }
}