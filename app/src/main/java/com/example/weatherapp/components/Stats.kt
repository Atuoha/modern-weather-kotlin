package com.example.weatherapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun Stats(
    title: String, value: String,
    deg: String = "", img: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(end = 5.dp)
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = TextStyle(
                color = AppColors.statsTitleColor,
                fontSize = 14.sp,
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Box(
            modifier = Modifier
                .size(90.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = AppColors.statsBg)
        ) {
            Image(
                painter = painterResource(id = img),
                contentDescription = "Image",
                modifier = Modifier
                    .padding(15.dp)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "$value $deg",
            textAlign = TextAlign.Center,
            style = TextStyle(
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )
        )
    }
}