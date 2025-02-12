package com.busra.meditrack

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.busra.meditrack.entity.Medication

class AlarmScheduler(private val context: Context) {
    fun scheduleAlarm(medication: Medication) {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("medication_name", medication.name)
            putExtra("medication_dose", medication.dose)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context, medication.id, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        if (medication.repeatInterval != null) {
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                medication.timeInMillis,
                medication.repeatInterval,
                pendingIntent
            )
        } else {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                medication.timeInMillis,
                pendingIntent
            )
        }
    }
}

