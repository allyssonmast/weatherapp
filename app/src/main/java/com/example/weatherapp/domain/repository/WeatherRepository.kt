package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.util.Resource

interface WeatherRepository {
    suspend fun getWeather(query: String): Resource<Weather>
}