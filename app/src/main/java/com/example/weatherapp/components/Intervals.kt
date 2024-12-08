package com.example.weatherapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.utils.AppColors


@Composable
fun Intervals(
    title: String,
    date: String,
    time: String = "",
    img: Int,
    currentIndex: MutableState<Int>,
    index: Int,
) {


    Box(
        modifier = Modifier
            .width(90.dp)
            .height(150.dp)
            .padding(end = 5.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = if (currentIndex.value == index) AppColors.lighterPrimaryColor else AppColors.statsBg)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(top = 5.dp)
        ) {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = if (currentIndex.value == index) Color.White else AppColors.primaryColor,
                    fontSize = 14.sp,
                )
            )

            Image(
                painter = painterResource(id = img),
                contentDescription = "Image",
                modifier = Modifier
                    .padding(15.dp)
            )
            Text(
                text = date,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = if (currentIndex.value == index) Color.White else  Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                )
            )
            Text(
                text = time,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = if (currentIndex.value == index) Color.White else  Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                )
            )
        }

    }
}