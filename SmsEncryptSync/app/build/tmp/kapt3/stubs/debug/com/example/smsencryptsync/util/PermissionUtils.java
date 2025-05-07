package com.example.smsencryptsync.util;

/**
 * 权限管理工具类，处理应用所需的权限
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lcom/example/smsencryptsync/util/PermissionUtils;", "", "()V", "Companion", "app_debug"})
public final class PermissionUtils {
    public static final int PERMISSION_SMS_REQUEST_CODE = 100;
    public static final int PERMISSION_NOTIFICATION_REQUEST_CODE = 101;
    @org.jetbrains.annotations.NotNull
    public static final com.example.smsencryptsync.util.PermissionUtils.Companion Companion = null;
    
    public PermissionUtils() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u0010\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u0011\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/example/smsencryptsync/util/PermissionUtils$Companion;", "", "()V", "PERMISSION_NOTIFICATION_REQUEST_CODE", "", "PERMISSION_SMS_REQUEST_CODE", "checkAndRequestAllPermissions", "", "activity", "Landroid/app/Activity;", "hasNotificationPermission", "", "context", "Landroid/content/Context;", "hasSmsPermission", "openAppSettings", "requestNotificationPermission", "requestSmsPermission", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * 检查是否有接收短信的权限
         */
        public final boolean hasSmsPermission(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
            return false;
        }
        
        /**
         * 检查是否有发送通知的权限（Android 13及以上需要）
         */
        public final boolean hasNotificationPermission(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
            return false;
        }
        
        /**
         * 请求接收短信权限
         */
        public final void requestSmsPermission(@org.jetbrains.annotations.NotNull
        android.app.Activity activity) {
        }
        
        /**
         * 请求通知权限（Android 13及以上）
         */
        public final void requestNotificationPermission(@org.jetbrains.annotations.NotNull
        android.app.Activity activity) {
        }
        
        /**
         * 引导用户前往应用设置页面开启权限
         */
        public final void openAppSettings(@org.jetbrains.annotations.NotNull
        android.app.Activity activity) {
        }
        
        /**
         * 检查所有必需的权限
         */
        public final void checkAndRequestAllPermissions(@org.jetbrains.annotations.NotNull
        android.app.Activity activity) {
        }
    }
}