package com.example.weatherapp.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.R
import com.example.weatherapp.components.CityList
import com.example.weatherapp.components.ConfirmDeleteDialog
import com.example.weatherapp.view_model.WeatherViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedCitiesScreen(
    navController: NavController,
    viewModel: WeatherViewModel = hiltViewModel(),
) {

    val context = LocalContext.current

    LaunchedEffect(viewModel.getToastMessage()) {
        viewModel.getToastMessage()?.let { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            viewModel.setToastMessage()
        }
    }

    return Scaffold(
        containerColor = Color.White,

        topBar = {
            TopAppBar(
                title = { Text(text = "Saved Cities") },
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
            if (viewModel.cityWeathers.value.isNotEmpty())
                CityList(
                    cities = viewModel.cityWeathers.collectAsState(),
                    navController = navController
                ) {
                    viewModel.confirmDelete(it)
                }
            else
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.opps),
                        contentDescription = "Image"
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Opps! No note found.",
                        style = TextStyle(color = Color.LightGray),
                    )
                }

        }
        if (viewModel.showDeleteDialog.value) {
            ConfirmDeleteDialog(
                viewModel.showDeleteDialog,
                viewModel.cityToDelete,
                deleteCity = {
                    viewModel.deleteCity()
                }
            )
        }
    }
}


