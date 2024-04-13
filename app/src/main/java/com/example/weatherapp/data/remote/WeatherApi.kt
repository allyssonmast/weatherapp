package com.example.weatherapp.data.remote

import com.example.weatherapp.data.remote.model.WeatherResponse
import com.example.weatherapp.util.ApiConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("current")
    suspend fun getCurrentWeather(
        @Query("access_key") accessKey: String = ApiConstants.ACCESS_KEY,
        @Query("query") query: String
    ): Response<WeatherResponse>
}