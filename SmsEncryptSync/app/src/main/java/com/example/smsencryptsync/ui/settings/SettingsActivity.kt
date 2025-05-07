package com.example.smsencryptsync.ui.settings

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.smsencryptsync.R
import com.example.smsencryptsync.data.api.RetrofitClient
import com.example.smsencryptsync.data.pref.ConfigManager
import com.example.smsencryptsync.util.EncryptionHelper
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class SettingsActivity : AppCompatActivity() {

    private lateinit var etServerUrl: TextInputEditText
    private lateinit var etApiKey: TextInputEditText
    private lateinit var etEncryptionPassword: TextInputEditText
    private lateinit var btnSave: MaterialButton
    private lateinit var btnClear: MaterialButton
    private lateinit var tvDeviceId: TextView
    
    private lateinit var configManager: ConfigManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        
        // 初始化工具栏
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        // 初始化视图
        etServerUrl = findViewById(R.id.etServerUrl)
        etApiKey = findViewById(R.id.etApiKey)
        etEncryptionPassword = findViewById(R.id.etEncryptionPassword)
        btnSave = findViewById(R.id.btnSave)
        btnClear = findViewById(R.id.btnClear)
        tvDeviceId = findViewById(R.id.tvDeviceId)
        
        // 初始化配置管理器
        configManager = ConfigManager.getInstance(this)
        
        // 加载当前配置
        loadCurrentConfig()
        
        // 设置保存按钮点击事件
        btnSave.setOnClickListener {
            saveConfig()
        }
        
        // 设置清除按钮点击事件
        btnClear.setOnClickListener {
            clearConfig()
        }
        
        // 显示设备ID
        tvDeviceId.text = "设备ID: ${configManager.getDeviceId()}"
    }
    
    /**
     * 加载当前配置
     */
    private fun loadCurrentConfig() {
        etServerUrl.setText(configManager.getServerUrl())
        etApiKey.setText(configManager.getApiKey())
        etEncryptionPassword.setText(configManager.getEncryptionPassword())
    }
    
    /**
     * 保存配置
     */
    private fun saveConfig() {
        val serverUrl = etServerUrl.text.toString().trim()
        val apiKey = etApiKey.text.toString().trim()
        val encryptionPassword = etEncryptionPassword.text.toString().trim()
        
        // 验证输入
        if (serverUrl.isEmpty() || apiKey.isEmpty() || encryptionPassword.isEmpty()) {
            Toast.makeText(this, R.string.error_empty_fields, Toast.LENGTH_SHORT).show()
            return
        }
        
        // 保存配置
        configManager.saveServerUrl(serverUrl)
        configManager.saveApiKey(apiKey)
        configManager.saveEncryptionPassword(encryptionPassword)
        
        // 确保盐值已生成
        val salt = configManager.getSalt()
        
        // 重置API服务（因为URL可能改变）
        RetrofitClient.resetApiService()
        
        Toast.makeText(this, R.string.settings_saved, Toast.LENGTH_SHORT).show()
        finish()
    }
    
    /**
     * 清除配置
     */
    private fun clearConfig() {
        etServerUrl.setText("")
        etApiKey.setText("")
        etEncryptionPassword.setText("")
        
        configManager.clearConfig()
        
        Toast.makeText(this, "配置已清除", Toast.LENGTH_SHORT).show()
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}