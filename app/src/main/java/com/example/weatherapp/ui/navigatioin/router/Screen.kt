package com.example.weatherapp.ui.navigatioin.router

sealed class Screen(val route: String){
    object WeatherScreen: Screen("Weather_screen")
    object SearchScreen: Screen("Search_screen")
}