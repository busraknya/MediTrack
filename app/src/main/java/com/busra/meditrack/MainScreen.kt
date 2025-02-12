package com.busra.meditrack

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.busra.meditrack.viewmodel.MedicationViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController, viewModel: MedicationViewModel) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("İlaç Hatırlatma") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("add_medication")
            }) {
                Icon(Icons.Default.Add, contentDescription = "İlaç Ekle")
            }
        }
    ) { innerPadding ->
        MedicationHistoryScreen(viewModel)
    }
}
