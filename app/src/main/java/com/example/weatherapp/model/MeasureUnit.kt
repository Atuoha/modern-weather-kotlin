package com.example.weatherapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.utils.AppStrings
import org.jetbrains.annotations.NotNull

@Entity(tableName = AppStrings.UNIT_TABLE)
data class MeasureUnit(

    @PrimaryKey
    @ColumnInfo(name= "unit")
    var unit: String
)
