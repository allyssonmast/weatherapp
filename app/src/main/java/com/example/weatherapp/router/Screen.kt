package com.example.weatherapp.router

sealed class Screen(val route: String){
    data object WeatherScreen: Screen("Weather_screen")
    data object SearchScreen: Screen("Search_screen")
}