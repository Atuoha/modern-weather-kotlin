package com.example.weatherapp.components

import androidx.compose.foundation.background
import com.example.weatherapp.utils.AppColors
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputField(
    valueState: MutableState<String>,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Search,
    onAction: KeyboardActions = KeyboardActions.Default,
) {
    return OutlinedTextField(
        value = valueState.value,
        onValueChange = { newValue ->
            if (newValue.all { char ->
                    char.isWhitespace() || char.isLetter()
                }) valueState.value = newValue
        },
        modifier = Modifier
            .fillMaxWidth()

            .clip(shape = RoundedCornerShape(15.dp)),

        placeholder = {
            Text(
                text = "Search a city", style = TextStyle(
                    color = AppColors.greyShade,
                )
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = AppColors.textBoxFillColor,
            unfocusedBorderColor = Color.Transparent
        ),
        enabled = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = onAction,
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.LocationOn,
                contentDescription = "Icon",
                tint = AppColors.primaryColor,
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = "Icon",
                tint = AppColors.primaryColor,
            )
        },
        textStyle = TextStyle(
            color =
            MaterialTheme.colorScheme.onBackground,
            fontSize = 18.sp
        ),
        shape = RoundedCornerShape(15.dp)
    )

}