package com.example.weatherapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.utils.AppColors


@Composable
fun WeatherCard(
    weather: String,
    img: Int, value: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(AppColors.primaryColor),
            elevation = CardDefaults.cardElevation(18.dp)
        ) {}

        Image(
            painter = painterResource(id = img),
            contentDescription = "Overlay Image",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(y = (-50).dp)
                .padding(end = 10.dp, top = 10.dp)
        )

        Text(
            text = weather,
            style = TextStyle(
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
            ),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 30.dp, bottom = 20.dp)
        )

        Text(
            text = "$value°",
            style = TextStyle(
                color = AppColors.degreeText,
                fontSize = 80.sp,
                fontWeight = FontWeight.Medium,
            ),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 20.dp)
        )
    }
}
