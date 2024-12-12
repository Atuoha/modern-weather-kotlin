package com.example.weatherapp.screens
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
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
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
import com.example.weatherapp.model.intervals
import com.example.weatherapp.widgets.SearchCityFloatBTN

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val intervalIndex = remember {
        mutableStateOf(0)
    }
    val expanded = remember { mutableStateOf(false) }

    return Scaffold(
        floatingActionButton = {
            SearchCityFloatBTN(
                icon = Icons.Rounded.Save
            ){
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
                HeaderSection(city = "Ireland", date = "Sunday, 8th December, 2024")
                Spacer(modifier = Modifier.height(15.dp))
                WeatherCard(img = R.drawable.a13d, value = "270", weather = "Snowy")
                Spacer(modifier = Modifier.height(50.dp))
                Row {
                    Stats(
                        title = "Min Temp", value = "304.1",
                        deg = "C", img = R.drawable.sleet
                    )
                    Stats(
                        title = "Humidity", value = "504.1",
                        img = R.drawable.humidity
                    )
                    Stats(
                        title = "Max Temp", value = "504.1",
                        deg = "C", img = R.drawable.maxtemp
                    )
                    Stats(
                        title = "Wind Speed", value = "104.1",
                        img = R.drawable.windspeed,
                        deg = "km\\h",
                    )


                }
                Spacer(modifier = Modifier.height(30.dp))
                OverlaySection()
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow {
                    items(intervals) { interval ->
                        Box(modifier = Modifier.clickable {
                            intervalIndex.value = interval.id.toInt()
                        }) {
                            Intervals(
                                title = interval.title, date = interval.date,
                                time = interval.time, img = interval.img,
                                currentIndex = intervalIndex,
                                index = interval.id.toInt(),
                            )
                        }
                    }
                }
            }
        }
    }
}

