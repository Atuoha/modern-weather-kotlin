package com.example.weatherapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CloudDone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherapp.R
import com.example.weatherapp.components.SearchCityContent
import com.example.weatherapp.components.TextInputField
import com.example.weatherapp.enums.WeatherScreens
import com.example.weatherapp.widgets.SearchCityAppBar
import com.example.weatherapp.widgets.SearchCityFloatBTN


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchCityScreen(navController: NavController) {
    val cityValue = remember {
        mutableStateOf("")
    }

    val error = false
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

    return Scaffold(
        containerColor = Color.White,
        floatingActionButton = {
            SearchCityFloatBTN(icon = Icons.Rounded.CloudDone) {
                navController.navigate(route = WeatherScreens.HomeScreen.name)
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

                TextInputField(valueState = cityValue)
                SearchCityContent(
                    img = responseImage.value,
                    desc = msg.value
                )

            }
        }
    }
}



