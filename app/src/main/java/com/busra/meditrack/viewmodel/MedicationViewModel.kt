package com.busra.meditrack.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.busra.meditrack.entity.Medication
import com.busra.meditrack.repository.MedicationRepository
import kotlinx.coroutines.launch

class MedicationViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MedicationRepository = MedicationRepository(application)
    val allMedications: LiveData<List<Medication>> = repository.allMedications

    fun insert(medication: Medication) = viewModelScope.launch {
        repository.insert(medication)
    }

}