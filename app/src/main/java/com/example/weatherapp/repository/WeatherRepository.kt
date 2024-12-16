package com.example.weatherapp.repository

import android.util.Log
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.weather.Weather
import com.example.weatherapp.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherApi: WeatherApi) {

    private val dataOrException = DataOrException<Weather, Boolean, Exception>()

    suspend fun getWeather(city: String): DataOrException<Weather, Boolean, Exception> {
        dataOrException.data = null
        dataOrException.error = null
        dataOrException.loading = true

        try {
            val weatherResponse = weatherApi.getWeather(city = city)
            if (weatherResponse.cod == "200") {
                dataOrException.data = weatherResponse
            } else {
                val errorMessage =
                    "Error: ${weatherResponse.cod}, Message: ${weatherResponse.message}"
                dataOrException.error = Exception(errorMessage)
            }

        } catch (e: Exception) {
            dataOrException.error = e
            Log.d("Weather Exception", "Exception: Exception getting weather ${e.message}")
        } finally {
            dataOrException.loading = true
        }

        return dataOrException
    }
}