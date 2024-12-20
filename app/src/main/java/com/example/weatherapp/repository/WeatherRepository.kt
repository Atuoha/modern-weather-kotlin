package com.example.weatherapp.repository

import android.util.Log
import com.example.weatherapp.data.CityDatabaseDAO
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.CityWeather
import com.example.weatherapp.model.MeasureUnit
import com.example.weatherapp.model.weather.Weather
import com.example.weatherapp.network.WeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi,
    private val cityDatabaseDAO: CityDatabaseDAO
) {

    private val dataOrException = DataOrException<Weather, Boolean, Exception>()

    suspend fun getWeather(city: String,units:String): DataOrException<Weather, Boolean, Exception> {
        dataOrException.data = null
        dataOrException.error = null
        dataOrException.loading = true

        try {
            val weatherResponse = weatherApi.getWeather(city = city,units = units)
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



    // DATABASE
    fun getCities(): Flow<List<CityWeather>> =
        cityDatabaseDAO.getCities().flowOn(Dispatchers.IO).conflate()

    suspend fun addCity(city: CityWeather) = cityDatabaseDAO.createCity(city)
    suspend fun deleteCity(city: CityWeather) = cityDatabaseDAO.deleteCity(city)

    suspend fun deleteAllCities() = cityDatabaseDAO.deleteAllCities()


    // unit
    fun getUnit(): Flow<MeasureUnit> = cityDatabaseDAO.getUnit().flowOn(Dispatchers.IO).conflate()

    suspend fun saveUnit(unit:MeasureUnit)= cityDatabaseDAO.saveUnit(unit)

    suspend fun updateUnit(unit: MeasureUnit) = cityDatabaseDAO.updateUnit(unit)

    suspend fun deleteUnit(unit: MeasureUnit) = cityDatabaseDAO.deleteUnit(unit)

}