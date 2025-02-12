package com.busra.meditrack

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.busra.meditrack.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            val dao = AppDatabase.getDatabase(context).medicationDao()
            CoroutineScope(Dispatchers.IO).launch {
                val medications = dao.getAllMedications().value ?: emptyList()
                medications.forEach {
                    if (!it.isTaken) {
                        AlarmScheduler(context).scheduleAlarm(it)
                    }
                }
            }
        }
    }
}
