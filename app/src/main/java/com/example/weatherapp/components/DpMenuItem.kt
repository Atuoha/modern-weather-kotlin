package com.example.weatherapp.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.weatherapp.utils.AppColors


@Composable
 fun DpMenuItem(
    expanded: MutableState<Boolean>,
    icon: ImageVector,
    title: String,
    action: () -> Unit
) {
    DropdownMenuItem(
        onClick = {
            expanded.value = false
            action()
        },
        text = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Icon",
                    tint = AppColors.statsTitleColor,
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = title)
            }
        }
    )
}
