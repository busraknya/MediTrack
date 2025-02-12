package com.busra.meditrack

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import com.busra.meditrack.entity.Medication
import com.busra.meditrack.viewmodel.MedicationViewModel

@Composable
fun MedicationHistoryScreen(viewModel: MedicationViewModel) {
    val context = LocalContext.current
    val medications = remember { mutableStateOf(listOf<Medication>()) }

    // LiveData'nın değişimlerini manuel olarak gözlemliyoruz
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner) {
        // LiveData'ya gözlemci ekliyoruz
        viewModel.allMedications.observe(lifecycleOwner) { newMedications ->
            medications.value = newMedications
        }
    }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(medications.value) { medication ->
            Text("${medication.name} - ${medication.dose}")
            Divider()
        }
    }
}
