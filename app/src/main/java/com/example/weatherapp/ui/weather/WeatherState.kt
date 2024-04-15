package com.example.weatherapp.ui.weather

import com.example.weatherapp.domain.model.Weather

data class WeatherState(
    val isLoading: Boolean = false,
    val weather: Weather? = null,
    val city: String?= null,
    val isConnected:Boolean=false,
    val error: String? = null
)
