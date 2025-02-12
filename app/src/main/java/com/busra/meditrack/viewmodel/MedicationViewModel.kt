package com.busra.meditrack.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.busra.meditrack.database.AppDatabase
import com.busra.meditrack.entity.Medication
import com.busra.meditrack.repository.MedicationRepository
import kotlinx.coroutines.launch

class MedicationViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MedicationRepository
    val allMedications: LiveData<List<Medication>>

    init {
        val dao = AppDatabase.getDatabase(application).medicationDao()
        repository = MedicationRepository(dao)
        allMedications = repository.allMedications
    }

    fun insert(medication: Medication) = viewModelScope.launch {
        repository.insert(medication)
    }

    fun delete(medication: Medication) = viewModelScope.launch {
        repository.delete(medication)
    }
}