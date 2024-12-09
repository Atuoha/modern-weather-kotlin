package com.example.weatherapp.components

import com.example.weatherapp.model.CityWeather
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState



@Composable
fun ConfirmDeleteDialog(
    showDeleteDialog: MutableState<Boolean>,
    cityToDelete: MutableState<CityWeather?>,
    deleteCity: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { showDeleteDialog.value = false },
        confirmButton = {
            Button(onClick = { deleteCity() }) {
                Text("Delete")
            }
        },
        dismissButton = {
            Button(onClick = {
                showDeleteDialog.value = false
                cityToDelete.value = null
            }) {
                Text("Cancel")
            }
        },
        title = { Text("Delete city: ${cityToDelete.value?.city}") },
        text = { Text("Are you sure you want to delete this city?") }
    )
}