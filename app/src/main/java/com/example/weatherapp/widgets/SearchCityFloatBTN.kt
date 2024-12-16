package com.example.weatherapp.widgets

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.weatherapp.utils.AppColors

@Composable
fun SearchCityFloatBTN(icon: ImageVector, action: () -> Unit) {
    FloatingActionButton(
        shape = CircleShape,
        containerColor = AppColors.primaryColor,
        onClick = { action() }) {

        Icon(
            imageVector = icon,
            contentDescription = "Icon",
            tint = Color.White
        )
    }
}
