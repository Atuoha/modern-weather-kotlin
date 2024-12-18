package com.example.weatherapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.utils.AppStrings
import java.time.Instant
import java.util.Date
import java.util.UUID

@Entity(tableName = AppStrings.CITY_TABLE)
data class CityWeather(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "city")
    var city: String,

    @ColumnInfo(name = "country")
    var country: String,

    @ColumnInfo(name = "weather")
    var weather: String,

    @ColumnInfo(name = "createdAt")
    val date: Date = Date.from(Instant.now())
)