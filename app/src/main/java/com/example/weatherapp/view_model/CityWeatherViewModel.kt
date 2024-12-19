package com.example.weatherapp.view_model

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.CityWeather
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityWeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    private var _cityWeathers = MutableStateFlow<List<CityWeather>>(emptyList())
    var cityWeathers = _cityWeathers.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            weatherRepository.getCities().distinctUntilChanged().collect { cityList ->
                if (!cityList.isNullOrEmpty()) {
                    _cityWeathers.value = cityList
                }

            }
        }
    }

    val cityToDelete = mutableStateOf<CityWeather?>(null)
    val showDeleteDialog = mutableStateOf(false)
    private val toastMessage = mutableStateOf<String?>(null)


    fun getToastMessage(): String? {
        return toastMessage.value
    }

    fun setToastMessage() {
        toastMessage.value = null
    }


    fun deleteCity() {
        cityToDelete.value?.let { cityWeather ->
            viewModelScope.launch {
                weatherRepository.deleteCity(cityWeather)
            }
        }
        showDeleteDialog.value = false
        toastMessage.value = "City deleted successfully"
    }

    // confirm edit
    fun confirmDelete(cityWeather: CityWeather) {
        cityToDelete.value = cityWeather
        showDeleteDialog.value = true
    }


    fun saveCity(cityWeather: CityWeather) {
        viewModelScope.launch { weatherRepository.addCity(cityWeather) }
        toastMessage.value = "City saved successfully"
    }

}