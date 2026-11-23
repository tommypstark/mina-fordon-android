package com.example.minafordon

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class VehicleUiState(
    val newName: String = "",
    val newType: String = "Bil",
    val vehicles: List<Vehicle> = startVehicles
)

class VehicleViewModel : ViewModel() {
    var uiState by mutableStateOf(VehicleUiState())
        private set

    private var nextId = 3

    fun onNewNameChange(text: String) {
        uiState = uiState.copy(newName = text)
    }

    fun onNewTypeChange(text: String) {
        uiState = uiState.copy(newType = text)
    }

    fun addVehicle() {
        val name = uiState.newName.trim()

        if (name.isEmpty()) return

        Log.d("Mina fordon", "Fordon tillagt: $name")

        val vehicle = Vehicle(
            id = nextId,
            name = name,
            type = uiState.newType
        )

        nextId = nextId + 1

        uiState = uiState.copy(
            newName = "",
            newType = "Bil",
            vehicles = uiState.vehicles + vehicle
        )
    }

}