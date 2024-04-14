package com.example.weatherapp.domain.usecase

import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.util.Resource
import javax.inject.Inject

class SearchWeatherUsecase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(query: String): Resource<Weather> {

        return if (query.isNotBlank()) {
            weatherRepository.getWeather(query)
        } else {
            Resource.Error("Query cannot be blank")
        }
    }
}