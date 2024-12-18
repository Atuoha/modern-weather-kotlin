package com.example.weatherapp.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapp.enums.WeatherScreens
import com.example.weatherapp.model.weather.Weather
import com.example.weatherapp.screens.AboutAppScreen
import com.example.weatherapp.screens.HomeScreen
import com.example.weatherapp.screens.SavedCitiesScreen
import com.example.weatherapp.screens.SearchCityScreen
import com.example.weatherapp.screens.SettingsScreen
import com.example.weatherapp.screens.SplashScreen
import com.google.gson.Gson

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()



    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name) {
        composable(WeatherScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(WeatherScreens.SearchScreen.name) {
            SearchCityScreen(navController = navController)
        }

        composable(
            route = WeatherScreens.HomeScreen.name + "/{weather}",
            arguments = listOf(navArgument("weather") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val weatherJson = backStackEntry.arguments?.getString("weather") ?: ""
            val weather = Gson().fromJson(weatherJson, Weather::class.java)
            HomeScreen(navController = navController, weather = weather)
        }

        composable(WeatherScreens.SavedCityScreen.name) {
            SavedCitiesScreen(navController = navController)
        }

        composable(WeatherScreens.SettingsScreen.name) {
            SettingsScreen(navController = navController)
        }

        composable(WeatherScreens.AboutScreen.name) {
            AboutAppScreen(navController = navController)
        }
    }
}