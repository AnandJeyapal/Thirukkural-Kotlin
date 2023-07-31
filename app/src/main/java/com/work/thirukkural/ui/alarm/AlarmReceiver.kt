package com.work.thirukkural.ui.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.work.thirukkural.utils.showNotification

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("XXX", "OnReceive")
        showNotification(context, intent?.getIntExtra("KuralId", 0) ?: 1,
            intent?.getStringExtra("KuralDescription") ?: "")
    }
}