package com.busra.meditrack.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.busra.meditrack.dao.MedicationDao
import com.busra.meditrack.database.AppDatabase
import com.busra.meditrack.entity.Medication

class MedicationRepository(application: Application) {
    private val medicationDao = AppDatabase.getDatabase(application).medicationDao()
    val allMedications: LiveData<List<Medication>> = medicationDao.getAllMedications()

    suspend fun insert(medication: Medication) {
        medicationDao.insertMedication(medication)
    }

    suspend fun update(medication: Medication) {
        medicationDao.updateMedication(medication)
    }

}