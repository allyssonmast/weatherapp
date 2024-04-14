package com.example.weatherapp.domain.model

data class Weather(
    val iconUrl: String,
    val temperature: Number,
    val weatherDescription: String,
    val windSpeed: Number,
    val feelsLike: Number,
    val humidity: Number,
    val name: String,
    val country: String,
    val localtime: String,
    val uvIndex: Number,
    val observationTime: String,
)