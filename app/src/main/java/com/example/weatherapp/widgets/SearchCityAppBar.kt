package com.example.weatherapp.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationCity
import androidx.compose.material.icons.rounded.SavedSearch
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Storage
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.enums.WeatherScreens
import com.example.weatherapp.utils.AppColors


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SearchCityAppBar(navController: NavController) {
    TopAppBar(
        title =
        {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Rounded.LocationCity,
                    contentDescription = "City",
                    tint = Color.White,
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Search a city",
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp,
                    ),
                )
            }

        },

        actions = {
            IconButton(onClick = {
                navController.navigate(route = WeatherScreens.SavedCityScreen.name)
            }) {
                Icon(
                    imageVector = Icons.Rounded.Storage,
                    contentDescription = "City",
                    tint = Color.White
                )
            }

            IconButton(onClick = {
                navController.navigate(route = WeatherScreens.SettingsScreen.name)
            }) {
                Icon(
                    imageVector = Icons.Rounded.Settings,
                    contentDescription = "Settings",
                    tint = Color.White
                )
            }
        },

        colors = topAppBarColors(
            AppColors.primaryColor
        )
    )
}