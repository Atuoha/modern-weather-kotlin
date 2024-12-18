package com.example.weatherapp.components

import com.example.weatherapp.model.CityWeather
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CityList(
    cities: State<List<CityWeather>>,
    navController: NavController,
    confirmDelete: (cityWeather: CityWeather) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(
                start = 18.dp,
                end = 18.dp
            )
    ) {
        items(cities.value.reversed()) { city ->
            CityListTile(
                cityWeather = city,
                navController = navController,
            ) {
                confirmDelete(city)
            }

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
