package com.example.weatherapp.domain.usecase

import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SearchWeatherUsecase @Inject constructor(private val weatherRepository: WeatherRepository ){
    fun invoke(query: String): Flow<Resource<Weather>> {
        return if (query.isNotBlank()) {
            weatherRepository.getWeather(query)
        } else {
            flowOf(Resource.Error("Query cannot be blank"))
        }
    }
}