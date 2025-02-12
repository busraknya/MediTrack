package com.busra.meditrack.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.busra.meditrack.dao.MedicationDao
import com.busra.meditrack.database.AppDatabase
import com.busra.meditrack.entity.Medication

class MedicationRepository(private val dao: MedicationDao) {
    val allMedications: LiveData<List<Medication>> = dao.getAllMedications()

    suspend fun insert(medication: Medication) {
        dao.insert(medication)
    }

    suspend fun delete(medication: Medication) {
        dao.delete(medication)
    }
}