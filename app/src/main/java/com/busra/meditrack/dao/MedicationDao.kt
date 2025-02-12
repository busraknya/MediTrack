package com.busra.meditrack.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.busra.meditrack.entity.Medication

@Dao
interface MedicationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(medication: Medication)

    @Query("SELECT * FROM medications ORDER BY timeInMillis ASC")
    fun getAllMedications(): LiveData<List<Medication>>

    @Delete
    suspend fun delete(medication: Medication)
}
