package com.example.smsencryptsync.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.smsencryptsync.R
import com.example.smsencryptsync.data.model.DecryptedSms
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * 短信列表适配器，用于在RecyclerView中展示短信
 */
class SmsAdapter : ListAdapter<DecryptedSms, SmsAdapter.SmsViewHolder>(SmsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sms, parent, false)
        return SmsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SmsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    /**
     * 短信ViewHolder，负责绑定短信数据和视图
     */
    class SmsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvSender: TextView = itemView.findViewById(R.id.tvSender)
        private val tvTime: TextView = itemView.findViewById(R.id.tvTime)
        private val tvMessage: TextView = itemView.findViewById(R.id.tvMessage)
        private val ivSyncStatus: ImageView = itemView.findViewById(R.id.ivSyncStatus)
        
        // 日期格式化器
        private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        
        fun bind(sms: DecryptedSms) {
            // 设置发件人
            tvSender.text = sms.sender
            
            // 格式化并设置时间
            val date = Date(sms.timestamp)
            tvTime.text = dateFormat.format(date)
            
            // 设置消息内容
            tvMessage.text = sms.body
            
            // 设置同步状态图标
            if (sms.isSyncedUp) {
                ivSyncStatus.visibility = View.VISIBLE
                ivSyncStatus.setImageResource(android.R.drawable.ic_popup_sync)
            } else {
                ivSyncStatus.visibility = View.GONE
            }
        }
    }
    
    /**
     * DiffUtil回调，用于高效更新列表
     */
    class SmsDiffCallback : DiffUtil.ItemCallback<DecryptedSms>() {
        override fun areItemsTheSame(oldItem: DecryptedSms, newItem: DecryptedSms): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DecryptedSms, newItem: DecryptedSms): Boolean {
            return oldItem == newItem
        }
    }
}