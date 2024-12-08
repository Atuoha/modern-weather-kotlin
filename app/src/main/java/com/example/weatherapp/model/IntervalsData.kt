package com.example.weatherapp.model

import com.example.weatherapp.R

data class IntervalsData(
    var id: String,
    var title: String,
    var date: String,
    var time: String,
    var img: Int
)

val intervals = listOf<IntervalsData>(
    IntervalsData(
        id = "0",
        title = "Cloud", date = "Mon, 9th",
        time = "12pm", img = R.drawable.a04d
    ),
    IntervalsData(
        id = "1",
        title = "Sunny", date = "Mon, 9th",
        time = "12pm", img = R.drawable.a01d
    ),
    IntervalsData(
        id = "2",
        title = "Rainy", date = "Mon, 9th",
        time = "12pm", img = R.drawable.a09n
    ),
    IntervalsData(
        id = "3",
        title = "Stormy", date = "Mon, 9th",
        time = "12pm", img = R.drawable.a11d
    ),
    IntervalsData(
        id = "4",
        title = "Snow", date = "Mon, 9th",
        time = "12pm", img = R.drawable.a13d
    )
)