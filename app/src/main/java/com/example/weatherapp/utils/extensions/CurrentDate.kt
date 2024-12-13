package com.example.weatherapp.utils.extensions

import java.text.SimpleDateFormat
import java.util.*

fun currentDate(): String {
    val calendar = Calendar.getInstance()
    val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

    val dateFormat = SimpleDateFormat("EEEE, d'th' MMMM, yyyy", Locale.getDefault())

    var formattedDate = dateFormat.format(calendar.time)

    formattedDate = formattedDate.replace(Regex("(\\d)(?=\\s)")) {
        val day = it.value.toInt()
        when (day % 10) {
            1 -> "${it.value}st"
            2 -> "${it.value}nd"
            3 -> "${it.value}rd"
            else -> "${it.value}th"
        }
    }

    return formattedDate
}