package com.busra.meditrack

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.busra.meditrack.entity.Medication
import com.busra.meditrack.viewmodel.MedicationViewModel

@Composable
fun AddMedicationScreen(viewModel: MedicationViewModel, navController: NavController) {
    var name by remember { mutableStateOf("") }
    var dose by remember { mutableStateOf("") }
    var timeInMillis by remember { mutableStateOf(0L) }
    var repeatInterval by remember { mutableStateOf<Long?>(null) }

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("İlaç Adı") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = dose,
            onValueChange = { dose = it },
            label = { Text("Dozaj") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = timeInMillis.toString(),
            onValueChange = { timeInMillis = it.toLongOrNull() ?: 0L },
            label = { Text("Alarm Zamanı (ms cinsinden)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = repeatInterval?.toString() ?: "",
            onValueChange = { repeatInterval = it.toLongOrNull() },
            label = { Text("Tekrarlama Aralığı (ms)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            val medication = Medication(name = name, dose = dose, timeInMillis = timeInMillis, repeatInterval = repeatInterval)
            viewModel.insert(medication)
            AlarmScheduler(context).scheduleAlarm(medication)
            navController.popBackStack()
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Kaydet")
        }
    }
}
