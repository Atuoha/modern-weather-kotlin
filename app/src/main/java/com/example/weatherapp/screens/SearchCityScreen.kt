package com.example.weatherapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CloudDone
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.R
import com.example.weatherapp.components.SearchCityContent
import com.example.weatherapp.components.TextInputField
import com.example.weatherapp.enums.WeatherScreens
import com.example.weatherapp.utils.helpers.LottieLoadingAnimation
import com.example.weatherapp.view_model.WeatherViewModel
import com.example.weatherapp.widgets.SearchCityAppBar
import com.example.weatherapp.widgets.SearchCityFloatBTN
import com.google.gson.Gson


@Composable
fun SearchCityScreen(
    navController: NavController,
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current
    val weather = viewModel.weather.value
    val weatherFound = remember {
        mutableStateOf(false)
    }

    val cityValue = remember {
        mutableStateOf("")
    }
    val responseImage = remember {
        mutableStateOf(R.drawable.location)
    }
    val msg =
        remember {
            mutableStateOf("Search by entering the name of the city")
        }

    val isLoading = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(weather) {

        when {
            weather.loading == true -> {
                isLoading.value = true
                responseImage.value = R.drawable.loading
                msg.value = "Loading weather data..."
            }

            weather.error != null -> {
                isLoading.value = false
                responseImage.value = R.drawable.not_found
                msg.value = weather.error?.message ?: "Unknown error"
                weatherFound.value = false
            }

            weather.data != null -> {
                isLoading.value = false
                responseImage.value = R.drawable.found
                msg.value = "Weather data for ${cityValue.value} is loaded successfully!"
                weatherFound.value = true
            }
        }
    }
    return Scaffold(
        containerColor = Color.White,
        floatingActionButton = {
            if (weatherFound.value) SearchCityFloatBTN(icon = Icons.Rounded.CloudDone) {
                val weatherJson = Gson().toJson(weather.data)
                navController.navigate("${WeatherScreens.HomeScreen.name}/$weatherJson")
            }
        },
        topBar = {
            SearchCityAppBar()
        }
    ) { paddingValue ->

        Box(modifier = Modifier.padding(paddingValue)) {
            Column(
                modifier = Modifier.padding(
                    top = 10.dp,
                    start = 18.dp,
                    end = 18.dp
                )
            ) {

                TextInputField(valueState = cityValue) {
                    viewModel.getWeather(cityValue.value)
                    focusManager.clearFocus()
                }
                if (weather.loading == true) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        LottieLoadingAnimation()
                    }
                }
                SearchCityContent(
                    img = responseImage.value,
                    desc = msg.value
                )


            }
        }
    }
}



