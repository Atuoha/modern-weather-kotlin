package com.example.weatherapp.data
import com.example.weatherapp.utils.AppStrings
import kotlinx.coroutines.flow.Flow
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.weatherapp.model.CityWeather
import com.example.weatherapp.model.MeasureUnit

@Dao
interface CityDatabaseDAO {

    @Query("SELECT * FROM ${AppStrings.CITY_TABLE}")
    fun getCities(): Flow<List<CityWeather>>

    @Query("SELECT * FROM ${AppStrings.CITY_TABLE} WHERE id=:id")
    suspend fun getCityById(id: String): CityWeather

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createCity(city: CityWeather)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCity(city: CityWeather)

    @Delete
    suspend fun deleteCity(city: CityWeather)

    @Query("DELETE FROM ${AppStrings.CITY_TABLE}")
    suspend fun deleteAllCities()

 // unit

    @Query("SELECT * FROM ${AppStrings.UNIT_TABLE}")
    fun getUnit(): Flow<MeasureUnit>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUnit(unit: MeasureUnit)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnit(unit: MeasureUnit)

    @Delete
    suspend fun deleteUnit(unit: MeasureUnit)
}