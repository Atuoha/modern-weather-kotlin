package com.example.weatherapp.widgets

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CloudDone
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.weatherapp.utils.AppColors

@Composable
fun SearchCityFloatBTN(action: () -> Unit) {
    FloatingActionButton(
        shape = CircleShape,
        containerColor = AppColors.primaryColor,
        onClick = { action() }) {

        Icon(
            imageVector = Icons.Rounded.CloudDone,
            contentDescription = "Icon",
            tint = Color.White
        )
    }
}
