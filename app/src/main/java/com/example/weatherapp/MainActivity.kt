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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.navigatioin.router.Screen
import com.example.weatherapp.ui.screens.weather.WeatherScreen
import com.example.weatherapp.ui.screens.weather.WeatherState
import com.example.weatherapp.ui.screens.weather.WeatherViewModel
import com.example.weatherapp.util.theme.WeatherAppTheme

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {}
        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        ))
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
                    ){
                        composable(
                            route = Screen.WeatherScreen.route
                        ){
                            val viewModel = hiltViewModel<WeatherViewModel>()
                            val state: WeatherState by viewModel.weatherState
                            WeatherScreen(
                                navController = navController,
                                state =state,
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
