package com.example.weatherapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.weatherapp.utils.AppColors
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.components.CityListTile
import com.example.weatherapp.components.ConfirmDeleteDialog
import com.example.weatherapp.model.CityWeather

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedCities() {
    val cities = listOf(
        CityWeather(city = "New York", initial = "NY", weather = "Sunny"),
        CityWeather(city = "Los Angeles", initial = "LA", weather = "Cloudy"),
        CityWeather(city = "Chicago", initial = "CHI", weather = "Windy"),
        CityWeather(city = "Houston", initial = "HOU", weather = "Rainy"),
        CityWeather(city = "San Francisco", initial = "SF", weather = "Foggy")
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
                    IconButton(onClick = { }) {
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
                    CityListTile(city = city.city, initial = city.initial, weather = city.weather) {
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


