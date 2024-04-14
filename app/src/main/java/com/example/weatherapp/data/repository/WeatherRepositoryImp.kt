package com.example.weatherapp.data.repository

import com.example.weatherapp.data.local.QueryDao
import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.data.remote.model.mapWeatherResponseToWeather
import com.example.weatherapp.domain.model.QueryEntity
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.util.Resource
import com.example.weatherapp.util.exceptions.ErrorResponse
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject


class WeatherRepositoryImp @Inject constructor(
    private val queryDao: QueryDao,
    private val weatherApi: WeatherApi
) :WeatherRepository{
    override suspend fun getWeather(query: String): Resource<Weather>{
            try {
                val response = weatherApi.getCurrentWeather(query = query)
                return if (response.isSuccessful) {
                    val weatherResponse = response.body()!!
                    queryDao.insertQuery(QueryEntity(query = query))
                    Resource.Success(mapWeatherResponseToWeather(weatherResponse))
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorBody, ErrorResponse::class.java)
                    Resource.Error(errorResponse?.error?.info ?: "Unknown API error")
                }
            } catch (e: IOException) {
                return Resource.Error("Network error: ${e.message}")
            } catch (e: HttpException) {
                return Resource.Error("HTTP error: ${e.code()}")
            } catch (e: Exception) {
                return Resource.Error("Unexpected error: ${e.message}")
            }
    }

}