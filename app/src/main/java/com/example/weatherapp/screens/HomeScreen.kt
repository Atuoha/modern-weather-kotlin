package com.example.weatherapp.screens

import androidx.compose.foundation.Image
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.components.HeaderSection
import com.example.weatherapp.components.Intervals
import com.example.weatherapp.components.OverlaySection
import com.example.weatherapp.components.Stats
import com.example.weatherapp.components.WeatherCard
import com.example.weatherapp.model.intervals

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val intervalIndex = remember {
        mutableStateOf(0)
    }

    return Scaffold(
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
                HeaderSection(city = "Lagos",date = "Sunday, 8th December, 2024")
                Spacer(modifier = Modifier.height(15.dp))
                WeatherCard(img = R.drawable.a11n, value = "320", weather = "Cloud")
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

