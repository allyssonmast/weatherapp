package com.example.weatherapp.domain.usecase

import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.util.Resource
import com.example.weatherapp.util.connetivity.GetConnectivity
import javax.inject.Inject

class SearchWeatherUsecase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val hasInternetConection: GetConnectivity
) {
    suspend operator fun invoke(query: String?): Resource<Weather> {
        return try {
            if (!hasInternetConection.isConnected()) {
                Resource.InternetConnection("No internet connection. Please check and try again")
            } else if (query.isNullOrBlank()) {
                Resource.Error("Query cannot be blank")
            } else {
                weatherRepository.getWeather(query)
            }
        } catch (e: Exception) {
            Resource.Error("Unexpected Error")
        }
    }
}