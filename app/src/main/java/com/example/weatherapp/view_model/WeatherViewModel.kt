package com.example.weatherapp.view_model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.CityWeather
import com.example.weatherapp.model.weather.Weather
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    private val data: MutableState<DataOrException<Weather, Boolean, Exception>> =
        mutableStateOf(DataOrException())
    val weather: State<DataOrException<Weather, Boolean, Exception>> = data
    private var _cityWeathers = MutableStateFlow<List<CityWeather>>(emptyList())
    var cityWeathers = _cityWeathers.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            weatherRepository.getCities().distinctUntilChanged().collect { cityList ->
                if (cityList.isNullOrEmpty()) {
                    Log.d("EMPTY", "EMPTY NOT LIST")
                } else {
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
        toastMessage.value = "City created successfully"
    }

    fun getWeather(city: String) {
        viewModelScope.launch {
            data.value = DataOrException(loading = true)
            Log.d("WeatherViewModel", "Loading weather for $city...")
            try {
                val weatherResponse = weatherRepository.getWeather(city = city)
                if (weatherResponse.data?.cod == "200") {
                    data.value = DataOrException(data = weatherResponse.data, loading = false)
                    Log.d("Weather Data", "Data: Data from weather ${weatherResponse.data}")
                } else {
                    data.value = DataOrException(
                        error = Exception("${weatherResponse.error}"),
                        loading = false,
                    )
                    Log.d("Weather Error", "Exception: ${weatherResponse.error?.message}")
                }
            } catch (e: Exception) {
                data.value = DataOrException(error = e, loading = false)
                Log.d("Weather Exception", "Exception: Exception getting weather ${e.message}")
            }
        }
    }


}