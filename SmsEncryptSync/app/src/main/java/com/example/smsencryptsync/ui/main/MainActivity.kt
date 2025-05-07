package com.example.smsencryptsync.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.smsencryptsync.R
import com.example.smsencryptsync.data.db.AppDatabase
import com.example.smsencryptsync.data.model.DecryptedSms
import com.example.smsencryptsync.data.pref.ConfigManager
import com.example.smsencryptsync.ui.settings.SettingsActivity
import com.example.smsencryptsync.util.PermissionUtils
import com.example.smsencryptsync.worker.SyncSmsWorker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyView: LinearLayout
    private lateinit var tvStatus: TextView
    private lateinit var fabSync: FloatingActionButton
    
    private lateinit var smsAdapter: SmsAdapter
    private lateinit var configManager: ConfigManager
    private lateinit var workManager: WorkManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // 初始化工具栏
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        
        // 初始化视图
        recyclerView = findViewById(R.id.recyclerViewSms)
        emptyView = findViewById(R.id.emptyView)
        tvStatus = findViewById(R.id.tvStatus)
        fabSync = findViewById(R.id.fabSync)
        
        // 初始化管理器
        configManager = ConfigManager.getInstance(this)
        workManager = WorkManager.getInstance(this)
        
        // 设置RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        smsAdapter = SmsAdapter()
        recyclerView.adapter = smsAdapter
        
        // 设置同步按钮点击事件
        fabSync.setOnClickListener {
            startSmsSync()
        }
        
        // 检查权限
        checkAndRequestPermissions()
        
        // 如果未配置，跳转到设置界面
        if (!configManager.isConfigured()) {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        
        // 加载短信列表
        loadSmsList()
    }
    
    /**
     * 加载短信列表
     */
    private fun loadSmsList() {
        val smsDao = AppDatabase.getInstance(this).smsDao()
        smsDao.getAllSms().observe(this) { smsList ->
            smsAdapter.submitList(smsList)
            updateEmptyView(smsList)
        }
    }
    
    /**
     * 更新空视图显示状态
     */
    private fun updateEmptyView(smsList: List<DecryptedSms>) {
        if (smsList.isEmpty()) {
            recyclerView.visibility = View.GONE
            emptyView.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            emptyView.visibility = View.GONE
        }
    }
    
    /**
     * 开始同步短信
     */
    private fun startSmsSync() {
        if (!configManager.isConfigured()) {
            Snackbar.make(
                findViewById(android.R.id.content),
                "请先完成设置",
                Snackbar.LENGTH_LONG
            ).setAction("设置") {
                startActivity(Intent(this, SettingsActivity::class.java))
            }.show()
            return
        }
        
        // 显示同步状态
        tvStatus.visibility = View.VISIBLE
        tvStatus.text = getString(R.string.syncing)
        fabSync.isEnabled = false
        
        // 创建同步工作请求
        val syncWorkRequest = OneTimeWorkRequestBuilder<SyncSmsWorker>().build()
        
        // 提交工作请求
        workManager.enqueue(syncWorkRequest)
        
        // 观察工作状态
        workManager.getWorkInfoByIdLiveData(syncWorkRequest.id).observe(this) { workInfo ->
            when (workInfo.state) {
                WorkInfo.State.SUCCEEDED -> {
                    tvStatus.text = getString(R.string.sync_success)
                    fabSync.isEnabled = true
                    // 3秒后隐藏状态
                    tvStatus.postDelayed({ tvStatus.visibility = View.GONE }, 3000)
                }
                WorkInfo.State.FAILED -> {
                    tvStatus.text = getString(R.string.sync_failed)
                    fabSync.isEnabled = true
                }
                WorkInfo.State.CANCELLED -> {
                    tvStatus.visibility = View.GONE
                    fabSync.isEnabled = true
                }
                else -> {
                    // 正在进行中，不做处理
                }
            }
        }
    }
    
    /**
     * 检查和请求所需权限
     */
    private fun checkAndRequestPermissions() {
        if (!PermissionUtils.hasSmsPermission(this)) {
            PermissionUtils.requestSmsPermission(this)
        }
        
        if (!PermissionUtils.hasNotificationPermission(this)) {
            PermissionUtils.requestNotificationPermission(this)
        }
    }
    
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        
        if (requestCode == PermissionUtils.PERMISSION_SMS_REQUEST_CODE) {
            if (!PermissionUtils.hasSmsPermission(this)) {
                Toast.makeText(this, R.string.permissions_required, Toast.LENGTH_LONG).show()
                Snackbar.make(
                    findViewById(android.R.id.content),
                    R.string.permissions_required,
                    Snackbar.LENGTH_INDEFINITE
                ).setAction(R.string.grant_permission) {
                    PermissionUtils.openAppSettings(this)
                }.show()
            }
        }
    }
    
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}