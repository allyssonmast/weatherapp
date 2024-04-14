package com.example.weatherapp.domain.usecase

import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SearchWeatherUsecase @Inject constructor(private val weatherRepository: WeatherRepository ){
    operator fun invoke(query: String): Flow<Resource<Weather>> = flow {
        try {
            if (query.isNotBlank()) {
                emit(Resource.Loading())
                val result = weatherRepository.getWeather(query)
                emit(result)
            } else {
                emit(Resource.Error("Query cannot be blank"))
            }
        }catch (e:Exception){
            emit(Resource.Error(e.message))
        }
    }
}