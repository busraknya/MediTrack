package com.busra.meditrack.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.busra.meditrack.entity.Medication

interface MedicationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedication(medication: Medication)

    @Query("SELECT * FROM medications")
    fun getAllMedications(): LiveData<List<Medication>>

    @Update
    suspend fun updateMedication(medication: Medication)
}