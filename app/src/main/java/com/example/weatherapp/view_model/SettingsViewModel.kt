package com.example.weatherapp.view_model

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.MeasureUnit
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val weatherRepository: WeatherRepository) : ViewModel() {

    private val _data = MutableStateFlow(MeasureUnit(unit = ""))
    val data = _data.asStateFlow()
    private val isFirstTime = mutableStateOf(false)
    private val toastMessage = mutableStateOf<String?>(null)

    init {
        viewModelScope.launch {
            weatherRepository.getUnit().distinctUntilChanged().collect{unit->
                if (unit == null || unit.unit.isNullOrEmpty()){
                    _data.value = MeasureUnit(unit = "Celsius °C")
                    isFirstTime.value = true
                }else{
                    _data.value = unit
                }
            }
        }
    }

    fun getToastMessage(): String? {
        return toastMessage.value
    }

    fun setToastMessage() {
        toastMessage.value = null
    }

    suspend fun saveUnit(unit: MeasureUnit) {
        viewModelScope.launch {
            if(isFirstTime.value){
                weatherRepository.saveUnit(unit)
                Log.d("BTN","SAVED......")
            }else{
                weatherRepository.updateUnit(unit)
                Log.d("BTN","UPDATED......")
            }

        }
        toastMessage.value = "Measurement saved to ${unit.unit}"

    }

    suspend fun updateUnit(unit: MeasureUnit) {
        viewModelScope.launch {
            weatherRepository.updateUnit(unit)
        }
        toastMessage.value = "Measurement updated to ${unit.unit}"
    }

    suspend fun deleteUnit(unit: MeasureUnit) {
        viewModelScope.launch {
            weatherRepository.deleteUnit(unit)
        }
        toastMessage.value = "Measurement deleted"
    }
}