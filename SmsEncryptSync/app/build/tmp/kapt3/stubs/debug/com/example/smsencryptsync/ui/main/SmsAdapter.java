package com.example.smsencryptsync.ui.main;

/**
 * 短信列表适配器，用于在RecyclerView中展示短信
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u000e\u000fB\u0005\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tH\u0016\u00a8\u0006\u0010"}, d2 = {"Lcom/example/smsencryptsync/ui/main/SmsAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/example/smsencryptsync/data/model/DecryptedSms;", "Lcom/example/smsencryptsync/ui/main/SmsAdapter$SmsViewHolder;", "()V", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "SmsDiffCallback", "SmsViewHolder", "app_debug"})
public final class SmsAdapter extends androidx.recyclerview.widget.ListAdapter<com.example.smsencryptsync.data.model.DecryptedSms, com.example.smsencryptsync.ui.main.SmsAdapter.SmsViewHolder> {
    
    public SmsAdapter() {
        super(null);
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.example.smsencryptsync.ui.main.SmsAdapter.SmsViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.example.smsencryptsync.ui.main.SmsAdapter.SmsViewHolder holder, int position) {
    }
    
    /**
     * DiffUtil回调，用于高效更新列表
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/example/smsencryptsync/ui/main/SmsAdapter$SmsDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/example/smsencryptsync/data/model/DecryptedSms;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
    public static final class SmsDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.example.smsencryptsync.data.model.DecryptedSms> {
        
        public SmsDiffCallback() {
            super();
        }
        
        @java.lang.Override
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull
        com.example.smsencryptsync.data.model.DecryptedSms oldItem, @org.jetbrains.annotations.NotNull
        com.example.smsencryptsync.data.model.DecryptedSms newItem) {
            return false;
        }
        
        @java.lang.Override
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull
        com.example.smsencryptsync.data.model.DecryptedSms oldItem, @org.jetbrains.annotations.NotNull
        com.example.smsencryptsync.data.model.DecryptedSms newItem) {
            return false;
        }
    }
    
    /**
     * 短信ViewHolder，负责绑定短信数据和视图
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/example/smsencryptsync/ui/main/SmsAdapter$SmsViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "dateFormat", "Ljava/text/SimpleDateFormat;", "ivSyncStatus", "Landroid/widget/ImageView;", "tvMessage", "Landroid/widget/TextView;", "tvSender", "tvTime", "bind", "", "sms", "Lcom/example/smsencryptsync/data/model/DecryptedSms;", "app_debug"})
    public static final class SmsViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView tvSender = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView tvTime = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView tvMessage = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageView ivSyncStatus = null;
        @org.jetbrains.annotations.NotNull
        private final java.text.SimpleDateFormat dateFormat = null;
        
        public SmsViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View itemView) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull
        com.example.smsencryptsync.data.model.DecryptedSms sms) {
        }
    }
}