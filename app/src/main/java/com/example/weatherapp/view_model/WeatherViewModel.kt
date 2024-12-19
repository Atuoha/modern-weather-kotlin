package com.example.weatherapp.view_model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.weather.Weather
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    private val data: MutableState<DataOrException<Weather, Boolean, Exception>> =
        mutableStateOf(DataOrException())
    val weather: State<DataOrException<Weather, Boolean, Exception>> = data

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