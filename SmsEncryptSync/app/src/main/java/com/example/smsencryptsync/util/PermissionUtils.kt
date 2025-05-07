package com.example.smsencryptsync.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import pub.devrel.easypermissions.EasyPermissions

/**
 * 权限管理工具类，处理应用所需的权限
 */
class PermissionUtils {
    companion object {
        // 权限请求码
        const val PERMISSION_SMS_REQUEST_CODE = 100
        const val PERMISSION_NOTIFICATION_REQUEST_CODE = 101
        
        /**
         * 检查是否有接收短信的权限
         */
        fun hasSmsPermission(context: Context): Boolean {
            return ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.RECEIVE_SMS
            ) == PackageManager.PERMISSION_GRANTED
        }
        
        /**
         * 检查是否有发送通知的权限（Android 13及以上需要）
         */
        fun hasNotificationPermission(context: Context): Boolean {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            } else {
                true // Android 13以下不需要单独请求通知权限
            }
        }
        
        /**
         * 请求接收短信权限
         */
        fun requestSmsPermission(activity: Activity) {
            EasyPermissions.requestPermissions(
                activity,
                "需要短信接收权限来监听和处理新的短信",
                PERMISSION_SMS_REQUEST_CODE,
                Manifest.permission.RECEIVE_SMS
            )
        }
        
        /**
         * 请求通知权限（Android 13及以上）
         */
        fun requestNotificationPermission(activity: Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                EasyPermissions.requestPermissions(
                    activity,
                    "需要通知权限来显示前台服务通知",
                    PERMISSION_NOTIFICATION_REQUEST_CODE,
                    Manifest.permission.POST_NOTIFICATIONS
                )
            }
        }
        
        /**
         * 引导用户前往应用设置页面开启权限
         */
        fun openAppSettings(activity: Activity) {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", activity.packageName, null)
            intent.data = uri
            activity.startActivity(intent)
        }
        
        /**
         * 检查所有必需的权限
         */
        fun checkAndRequestAllPermissions(activity: Activity) {
            val permissionsToRequest = mutableListOf<String>()
            
            // 检查短信接收权限
            if (!hasSmsPermission(activity)) {
                permissionsToRequest.add(Manifest.permission.RECEIVE_SMS)
            }
            
            // Android 13+ 检查通知权限
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && !hasNotificationPermission(activity)) {
                permissionsToRequest.add(Manifest.permission.POST_NOTIFICATIONS)
            }
            
            // 请求权限
            if (permissionsToRequest.isNotEmpty()) {
                ActivityCompat.requestPermissions(
                    activity,
                    permissionsToRequest.toTypedArray(),
                    PERMISSION_SMS_REQUEST_CODE
                )
            }
        }
    }
}