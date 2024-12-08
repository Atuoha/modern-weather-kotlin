package com.example.weatherapp.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.utils.AppColors


@Composable
fun HeaderSection(city:String,date:String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Rounded.LocationOn,
            contentDescription = "Chevron right",
            tint = AppColors.primaryColor,
        )
        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = city,
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium)
        )

    }

    Spacer(modifier = Modifier.height(5.dp))
    Text(
        text = date, style = TextStyle(
            color = AppColors.greyShade,
            fontSize = 14.sp,
        )
    )
}