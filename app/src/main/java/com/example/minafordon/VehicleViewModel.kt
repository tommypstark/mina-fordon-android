package com.example.minafordon

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class VehicleUiState(
    val newName: String = "",
    val vehicles: List<Vehicle> = startVehicles
)

class VehicleViewModel : ViewModel() {
    var uiState by mutableStateOf(VehicleUiState())
        private set

    fun onNewNameChange(text: String) {
        uiState = uiState.copy(newName = text)
    }
}