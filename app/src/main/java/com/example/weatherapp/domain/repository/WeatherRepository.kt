package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeather(query: String): Flow<Resource<Weather>>
}