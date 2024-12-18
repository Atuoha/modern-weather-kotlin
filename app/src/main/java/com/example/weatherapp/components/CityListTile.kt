package com.example.weatherapp.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.R
import com.example.weatherapp.enums.WeatherScreens
import com.example.weatherapp.model.CityWeather
import com.example.weatherapp.utils.AppColors
import com.example.weatherapp.view_model.WeatherViewModel
import com.google.gson.Gson
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

@Composable
fun CityListTile(
    cityWeather: CityWeather,
    viewModel: WeatherViewModel = hiltViewModel(),
    navController: NavController,
    confirmDelete: (cityWeather: CityWeather) -> Unit
) {
    val formatter = DateTimeFormatter.ofPattern("E, d, MMM", Locale.ENGLISH)
    val formattedDate = Instant.ofEpochMilli(cityWeather.date.time)
        .atZone(ZoneId.systemDefault())
        .format(formatter)

    val weather = viewModel.weather.value
    val weatherFound = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current



    LaunchedEffect(weather) {

        when {
            weather.loading == true -> {
                Toast.makeText(context, "Loading weather data...", Toast.LENGTH_SHORT).show()
            }

            weather.error != null -> {
                Toast.makeText(
                    context,
                    weather.error?.message ?: "Unknown error",
                    Toast.LENGTH_SHORT
                ).show()
                weatherFound.value = false
            }

            weather.data != null -> {
                Toast.makeText(
                    context,
                    "Weather data for ${cityWeather.city} is loaded successfully!",
                    Toast.LENGTH_SHORT
                ).show()
                    val weatherJson = Gson().toJson(weather.data)
                    navController.navigate("${WeatherScreens.HomeScreen.name}/$weatherJson")
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(AppColors.lighterPrimaryColor)
            .padding(15.dp)
            .clickable {
                viewModel.getWeather(cityWeather.city)



            }

    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth()
        ) {
            Row {
                Card(
                    modifier = Modifier.size(45.dp),
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(Color.White)
                ) {
                    Box(
                        contentAlignment = Alignment.Center, modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = cityWeather.country,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(
                                    top = 8.dp,
                                    start = if (cityWeather.country.length > 2) 0.dp else 5.dp
                                ),
                            style = TextStyle(color = AppColors.primaryColor)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(15.dp))
                Column {
                    Text(
                        text = cityWeather.city, style = TextStyle(fontWeight = FontWeight.Black)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = cityWeather.weather, style = TextStyle(fontSize = 12.sp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = formattedDate, style = TextStyle(
                            fontSize = 13.sp
                        )
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.DeleteForever,
                contentDescription = "Arrow",
                tint = Color.White,
                modifier = Modifier
                    .size(22.dp)
                    .clickable {
                        confirmDelete(cityWeather)
                    },
            )
        }
    }
}