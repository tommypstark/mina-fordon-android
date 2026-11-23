package com.example.minafordon

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.minafordon.ui.theme.MinaFordonTheme

data class Vehicle(
    val id: Int,
    val name: String,
    val type: String,
    val serviceDone: Boolean = false
)

val startVehicles = listOf(
    Vehicle(1, "Viarelli Enzo", "Moped"),
    Vehicle(2, "KIA Xceed", "Bil", true)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MinaFordonTheme {
                VehicleScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehicleScreen(viewModel: VehicleViewModel = viewModel()) {
    val state = viewModel.uiState

    Scaffold(
        topBar = {
            TopAppBar(
                   title = { Text("Mina fordon") }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            TextField(
                value = state.newName,
                onValueChange = { viewModel.onNewNameChange(it) },
                label = { Text("Namn") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            LazyColumn {
                items(state.vehicles) { vehicle ->
                    Text(text = "${vehicle.name} - ${vehicle.type}")
                }
            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun VehicleScreenPreview() {
    MinaFordonTheme {
        VehicleScreen(viewModel = VehicleViewModel())
    }
}