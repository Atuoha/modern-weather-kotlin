package com.example.weatherapp.model

data class Weather(
    var weatherCity: String = "",
    var weatherHumidity: Int = 0,
    var weatherTemp: Double = 0.0,
    var weatherMaxTemp: Double = 0.0,
    var weatherMinTemp: Double = 0.0,
    var weatherPressure: Int = 0,
    var weatherWindSpeed: Double = 0.0,
    var weatherWindDegree: Int = 0,
    var weatherSunrise: Int = 0,
    var weatherSunset: Int = 0,
    var weatherTimzone: Int = 0,
    var weatherWeatherStateName: String = "",
    var weatherFealsLike: Double = 0.0,
    var weatherImgUrl: String = "",
    var weatherDescription: String = "",
    var weatherCurrentState: String = "",
    var weatherCountry: String = ""
)
