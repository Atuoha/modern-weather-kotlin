package com.example.weatherapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.LocationCity
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherapp.R
import com.example.weatherapp.components.HeaderSection
import com.example.weatherapp.components.Intervals
import com.example.weatherapp.components.OverlaySection
import com.example.weatherapp.components.Stats
import com.example.weatherapp.components.WeatherCard
import com.example.weatherapp.components.DpMenuItem
import com.example.weatherapp.enums.WeatherScreens
import com.example.weatherapp.model.weather.Weather
import com.example.weatherapp.utils.extensions.currentDate
import com.example.weatherapp.widgets.SearchCityFloatBTN

@SuppressLint("DiscouragedApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, weather: Weather) {
    val context = LocalContext.current

    val intervalIndex = remember {
        mutableStateOf(0)
    }
    val expanded = remember { mutableStateOf(false) }

    return Scaffold(
        floatingActionButton = {
            SearchCityFloatBTN(
                icon = Icons.Rounded.Save
            ) {
                // Todo
            }
        },
        topBar = {
            TopAppBar(
                title =
                {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Image",
                        modifier = Modifier.size(50.dp)
                    )

                },
                actions = {
                    // Search Icon
                    IconButton(onClick = {
                        navController.navigate(route = WeatherScreens.SearchScreen.name)
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "Search",
                            tint = Color.Black
                        )
                    }
                    // More Options Icon
                    IconButton(onClick = { expanded.value = true }) {
                        Icon(
                            imageVector = Icons.Rounded.MoreVert,
                            contentDescription = "More Options",
                            tint = Color.Black
                        )
                    }
                    // Dropdown Menu
                    DropdownMenu(
                        modifier = Modifier.background(Color(0xffFEFBFE)),
                        expanded = expanded.value,
                        onDismissRequest = { expanded.value = false }
                    ) {
                        DpMenuItem(
                            expanded = expanded, title = "Saved Cities",
                            icon = Icons.Rounded.LocationCity
                        ) {
                            navController.navigate(route = WeatherScreens.SavedCityScreen.name)
                        }

                        DpMenuItem(
                            expanded = expanded, title = "Settings",
                            icon = Icons.Rounded.Settings
                        ) {
                            navController.navigate(route = WeatherScreens.SettingsScreen.name)
                        }
                        DpMenuItem(
                            expanded = expanded, title = "About",
                            icon = Icons.Rounded.Info
                        ) {
                            navController.navigate(route = WeatherScreens.AboutScreen.name)
                        }
                    }
                },
            )
        }
    ) {

        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier.padding(
                    start = 18.dp,
                    end = 18.dp, top = 10.dp
                )
            ) {
                val weatherData = weather.list[0]

                val icon = weatherData.weather[0].icon
                val weatherIcon = "a${icon}"
                val drawableId =
                    context.resources.getIdentifier(
                        weatherIcon,
                        "drawable", context.packageName
                    )


                HeaderSection(
                    city = weather.city.name,
                    country = weather.city.country,
                    date = currentDate(),
                )
                Spacer(modifier = Modifier.height(15.dp))
                WeatherCard(
                    img = drawableId,
                    value = String.format("%.0f", weatherData.main.temp),
                    weather = weatherData.weather[0].main,
                )
                Spacer(modifier = Modifier.height(50.dp))
                Row {
                    Stats(
                        title = "Min Temp", value = weatherData.main.temp_min.toString(),
                        deg = "C", img = R.drawable.sleet
                    )
                    Stats(
                        title = "Humidity", value = weatherData.main.humidity.toString(),
                        img = R.drawable.humidity
                    )
                    Stats(
                        title = "Max Temp", value = weatherData.main.temp_max.toString(),
                        deg = "C", img = R.drawable.maxtemp
                    )
                    Stats(
                        title = "Wind Speed", value = weatherData.wind.speed.toString(),
                        img = R.drawable.windspeed,
                        deg = "km\\h",
                    )


                }
                Spacer(modifier = Modifier.height(30.dp))
                OverlaySection(image = drawableId)
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow {
                    itemsIndexed(weather.list) { index, weatherInfo ->
                        Box(modifier = Modifier.clickable {
                            intervalIndex.value = index
                        }) {
                            val dateTime = weatherInfo.dt_txt
                            val parts = dateTime.split(" ")
                            val date = parts[0]
                            val time = parts[1]


                            val intervalIcon = weatherInfo.weather[0].icon
                            val intervalWeatherIcon = "a${intervalIcon}"
                            val intervalDrawId = context.resources.getIdentifier(
                                intervalWeatherIcon,
                                "drawable",
                                context.packageName
                            )

                            Intervals(
                                title = weatherInfo.weather[0].main, date = date,
                                time = time, img = intervalDrawId,
                                currentIndex = intervalIndex,
                                index = index,
                            )
                        }
                    }
                }
            }
        }
    }
}

