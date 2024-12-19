package com.example.weatherapp.screens

import android.widget.Toast
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
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.model.MeasureUnit
import com.example.weatherapp.view_model.SettingsViewModel
import com.example.weatherapp.widgets.SearchCityFloatBTN
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController, viewModel:SettingsViewModel = hiltViewModel()) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val currentData by viewModel.data.collectAsState()


    LaunchedEffect(viewModel.getToastMessage()) {
        viewModel.getToastMessage()?.let { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            viewModel.setToastMessage()
        }


    }
    val newUnit = remember { mutableStateOf("") }


    LaunchedEffect(currentData){
        newUnit.value = currentData.unit
    }

    return Scaffold(
        containerColor = Color.White,
        floatingActionButton = {
            if (currentData.unit != newUnit.value) SearchCityFloatBTN(
                icon = Icons.Rounded.Save
            ){
                scope.launch {
                    viewModel.deleteUnit(MeasureUnit(unit = currentData.unit))
                    viewModel.saveUnit(MeasureUnit(unit = newUnit.value))

                }
            }
        },
        topBar = {
            TopAppBar(
                title = { Text(text = "Settings") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color.White),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
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
                    text = "Click to change unit of measurement",
                    style = TextStyle(
                        color = AppColors.statsTitleColor,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Button(onClick = {
                    newUnit.value = if (newUnit.value == "Celsius °C") {
                        "Fahrenheit °F"
                    } else {
                        "Celsius °C"
                    }
                }) {
                    Text(
                        text = newUnit.value,
                        style = TextStyle(color = Color.White)
                    )
                }

            }
        }
    }
}



