package com.example.minafordon

import android.R.attr.text
import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

    val filtered = state.vehicles.filter {
        when (state.filter) {
            "Servade" -> it.serviceDone
            "Ej servade" -> !it.serviceDone
            else -> true
        }
    }
    val shown = if (state.sortByName) {
        filtered.sortedBy { it.name.lowercase() }
    } else {
        filtered
    }

    val serviced = state.vehicles.count { it.serviceDone }
    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mina fordon \uD83D\uDE98") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {

            TextField(
                value = state.newName,
                onValueChange = { viewModel.onNewNameChange(it) },
                label = { Text("Namn") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Row {
                Box {

                    Button(
                        onClick = {
                            expanded = !expanded
                        }  // för att kunna öppna/stänga menyn med samma knapp

                    ) {
                        Text(state.newType)
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        listOf("Bil", "Lastbil", "Moped", "Motorcykel").forEach { type ->
                            DropdownMenuItem(
                                text = { Text(type) },
                                onClick = {
                                    viewModel.onNewTypeChange(type)
                                    expanded = false
                                }
                            )
                        }

                    }
                }

                Button(
                    onClick = {
                        if (state.editingId == null) {
                            viewModel.addVehicle()
                        } else {
                            viewModel.saveEdit()
                        }
                    },
                    enabled = state.newName.isNotBlank()
                ) {
                    Text(if (state.editingId == null) "Lägg till" else "Spara")
                }
            }

            Text(
                text = "$serviced av ${state.vehicles.size} fordon servade",
                modifier = Modifier.padding(12.dp)
            )

            if (state.vehicles.isEmpty()) {
                Text(
                    text = "Inga fordon ännu. Lägg till ditt första fordon!",
                    modifier = Modifier.padding(start = 12.dp)
                )
            }

            val filters = listOf("Alla", "Servade", "Ej servade")

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                filters.forEach { filter ->
                    FilterChip(
                        selected = state.filter == filter,
                        onClick = { viewModel.onFilterChange(filter) },
                        label = { Text(filter) },
                    )
                }
                FilterChip(
                    selected = state.sortByName,
                    onClick = { viewModel.toggleSort() },
                    label = { Text("A–Ö") },
                )

            }

            LazyColumn {
                items(shown) { vehicle ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Checkbox(
                            checked = vehicle.serviceDone,
                            onCheckedChange = { viewModel.toggleService(vehicle.id) }
                        )

                        Text(text = "${vehicle.name} - ${vehicle.type}")

                        Spacer(modifier = Modifier.weight(1f))

                        IconButton(
                            onClick = { viewModel.startEdit(vehicle) },
                            modifier = Modifier.size(35.dp)
                        ) {
                            Text("✏️")
                        }

                        IconButton(
                            onClick = { viewModel.removeVehicle(vehicle.id) },
                            modifier = Modifier.size(35.dp)
                        ) {
                            Text("❌")
                        }
                    }

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