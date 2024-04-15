package com.example.weatherapp

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.router.Screen
import com.example.weatherapp.ui.weather.WeatherScreen
import com.example.weatherapp.ui.weather.WeatherState
import com.example.weatherapp.util.theme.WeatherAppTheme

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.example.weatherapp.ui.weather.WeatherViewModel as WeatherViewModel1

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {}
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )
        setContent {
            WeatherAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.WeatherScreen.route
                    ) {
                        composable(
                            route = Screen.WeatherScreen.route
                        ) {
                            // val viewModel = hiltViewModel<WeatherViewModel1>()
                            // val state: WeatherState by viewModel.weatherState

                            val state: WeatherState = fakeWeatherState
                            //val onSearch: (String) -> Unit = { query ->
                            //    viewModel.viewModelScope.launch {
                            //       viewModel.getWeatherByName(query)
                            //    }
                            // }
                            WeatherScreen(
                                state = state,
                                onSearch = {}
                            )
                        }
                        /*
                        composable(
                            route = Screen.CustomersScreen.route+"/{tableId}"
                        ){
                            CustomersScreen(navController)
                        }

                         */
                    }
                }
            }
        }
    }
}

val fakeWeatherState = WeatherState(
    isLoading = false,
    weather = Weather(
        iconUrl = "https://assets.weatherstack.com/images/wsymbols01_png_64/wsymbol_0001_sunny.png",
        temperature = 13,
        weatherDescription = "Sunny",
        windSpeed = 0,
        feelsLike = 13,
        humidity = 90,
        name = "New York",
        country = "United States of America",
        localtime = "2019-09-07 08:14",
        uvIndex = 4,
        observationTime = "12:14 PM"
    ),
    isConnected = true,
    error = null
)
