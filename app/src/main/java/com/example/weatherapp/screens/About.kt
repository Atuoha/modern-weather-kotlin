package com.example.weatherapp.screens

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutApp() {
    return Scaffold(
        containerColor = Color.White,

        topBar = {
            TopAppBar(
                title = { Text(text = "About App") },
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
            Column(
                modifier = Modifier
                    .padding(
                        start = 18.dp,
                        end = 18.dp
                    )
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,

                ) {

                Text(
                    text = "Weather App v1.0",
                    style = TextStyle(
                        color = AppColors.statsTitleColor,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "App powered by OpenWeatherMapAPI: https://openweathermapapi.com",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = AppColors.greyShade,
                        fontWeight = FontWeight.Light,
                        fontSize = 16.sp,
                    )
                )

            }
        }
    }
}



