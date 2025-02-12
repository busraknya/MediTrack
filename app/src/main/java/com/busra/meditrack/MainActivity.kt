package com.busra.meditrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.busra.meditrack.ui.theme.MediTrackTheme
import com.busra.meditrack.viewmodel.MedicationViewModel

class MainActivity : ComponentActivity() {
    private val medicationViewModel: MedicationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MediTrackTheme {
                // NavController'ı oluşturuyoruz
                val navController = rememberNavController()

                // Scaffold yapısını kullanıyoruz, içerik burada ekranda yer alacak
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        // NavHost kullanarak ekranlar arasında geçiş sağlıyoruz
                        NavHost(navController = navController, startDestination = "main_screen") {
                            composable("main_screen") {
                                MainScreen(navController = navController, viewModel = medicationViewModel)
                            }
                            composable("add_medication") {
                                AddMedicationScreen(viewModel = medicationViewModel, navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}
