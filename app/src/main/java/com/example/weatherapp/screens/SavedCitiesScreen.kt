package com.example.weatherapp.screens

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherapp.components.CityListTile
import com.example.weatherapp.components.ConfirmDeleteDialog
import com.example.weatherapp.model.CityWeather


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedCitiesScreen(navController: NavController) {
    val cities = listOf(
        CityWeather(city = "Lagos", country = "NG", weather = "Sunny"),
        CityWeather(city = "Dublin", country = "IE", weather = "Cloudy"),
        CityWeather(city = "Chicago", country = "US", weather = "Windy"),
        CityWeather(city = "Nairobi", country = "KY", weather = "Rainy"),
        CityWeather(city = "San Francisco", country = "US", weather = "Foggy")
    )

    val showDeleteDialog = remember {
        mutableStateOf(false)
    }

    val cityToDelete = remember {
        mutableStateOf<CityWeather?>(null)
    }

    return Scaffold(
        containerColor = Color.White,

        topBar = {
            TopAppBar(
                title = { Text(text = "Saved Cities") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color.White),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
            )
        }
    ) { paddingValue ->

        Box(modifier = Modifier.padding(paddingValue)) {
            LazyColumn(
                modifier = Modifier
                    .padding(
                        start = 18.dp,
                        end = 18.dp
                    )
            ) {
                items(cities) { city ->
                    CityListTile(city = city.city, initial = city.country, weather = city.weather) {
                        showDeleteDialog.value = true
                        cityToDelete.value = city
                    }

                }
            }



        }
        if (showDeleteDialog.value) {
            ConfirmDeleteDialog(
                showDeleteDialog,
                cityToDelete,
                deleteCity = {

                }
            )
        }
    }
}


