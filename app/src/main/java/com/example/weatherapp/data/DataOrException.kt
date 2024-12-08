package com.example.weatherapp.data

class DataOrException<T, Boolean, Exception>(
    var data: T? = null,
    var loading: Boolean? = null,
    var error: Exception? = null
)
