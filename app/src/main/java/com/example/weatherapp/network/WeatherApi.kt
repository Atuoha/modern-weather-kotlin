package com.example.weatherapp.network

import com.example.weatherapp.model.weather.Weather
import com.example.weatherapp.utils.AppStrings
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {

    @GET("data/2.5/forecast")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("units") units: String = "metrics",
        @Query("appid") appid: String = AppStrings.API_KEY
    ): Weather
}

