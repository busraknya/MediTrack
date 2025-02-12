package com.busra.meditrack

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val medicationName = intent.getStringExtra("medication_name") ?: "Bilinmeyen İlaç"
        val dose = intent.getStringExtra("medication_dose") ?: ""

        NotificationUtils.showNotification(context, medicationName, dose)
    }
}