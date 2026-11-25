package com.example.minafordon

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class VehicleUiState(
    val newName: String = "",
    val newType: String = "Bil",
    val vehicles: List<Vehicle> = startVehicles,
    val editingId: Int? = null,
    val filter: String = "Alla"
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

    fun toggleService(id: Int) {
        uiState = uiState.copy(
            vehicles = uiState.vehicles.map {
                if (it.id == id) it.copy(serviceDone = !it.serviceDone) else it
            }
        )
    }

    fun removeVehicle(id: Int) {
        uiState = uiState.copy(
            vehicles = uiState.vehicles.filter { it.id != id }
        )
        if (uiState.editingId == id) {
            uiState = uiState.copy(
                newName = "",
                newType = "Bil",
                editingId = null
            )
        }
    }

    fun startEdit(vehicle: Vehicle) {
        uiState = uiState.copy(
            newName = vehicle.name,
            newType = vehicle.type,
            editingId = vehicle.id,
        )
    }

    fun saveEdit() {
        val name = uiState.newName.trim()
        if (name.isEmpty()) return
        val id = uiState.editingId
        uiState = uiState.copy(
            newName = "",
            newType = "Bil",
            editingId = null,
            vehicles = uiState.vehicles.map {
                if (it.id == id) it.copy(
                    name = name,
                    type = uiState.newType
                ) else it
            },
        )
    }

    fun onFilterChange(filter: String) {
        uiState = uiState.copy(filter = filter)
    }

}