package com.example.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.data.CityDatabase
import com.example.weatherapp.data.CityDatabaseDAO
import com.example.weatherapp.network.WeatherApi
import com.example.weatherapp.utils.AppStrings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppBundle {

    @Provides
    @Singleton
    fun providesWeather(): WeatherApi {
        return Retrofit.Builder().baseUrl(AppStrings.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(WeatherApi::class.java)
    }


    @Singleton
    @Provides
    fun providesCityDao(cityDatabase: CityDatabase): CityDatabaseDAO = cityDatabase.cityDao()


    @Singleton
    @Provides
    fun providesDatabaseBuilder(@ApplicationContext context: Context): CityDatabase =
        Room.databaseBuilder(
            context = context,
            klass = CityDatabase::class.java,
            name = "cities_db"
        )
            .fallbackToDestructiveMigration().build()
}