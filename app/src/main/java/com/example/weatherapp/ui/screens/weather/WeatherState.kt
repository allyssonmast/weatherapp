package com.example.weatherapp.ui.screens.weather

import com.example.weatherapp.domain.model.Weather

data class WeatherState(
    val isLoading: Boolean = false,
    val weather: Weather? = null,
    val isConnected:Boolean=true,
    val error: String? = null
)
