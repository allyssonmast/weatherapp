package com.example.weatherapp.domain.model

data class Weather(
    val iconUrl: String,
    val temperature: Number,
    val weatherDescription: String,
    val windSpeed: Number,
    val feelsLike: Number,
    val humidity: Number
)