package com.example.weatherapp.domain.usecase

import com.example.weatherapp.data.geocoder.GeoCoding
import com.example.weatherapp.domain.location.LocationTracker
import com.example.weatherapp.util.Resource
import com.example.weatherapp.util.exceptions.LocationAndCityNameException
import com.example.weatherapp.util.exceptions.MissingCityNameError
import com.example.weatherapp.util.exceptions.MissingLocationError
import javax.inject.Inject

class GetUserLocationAndCityNameUseCase @Inject constructor(
    private val locationTracker: LocationTracker,
    private val geoCoding: GeoCoding,
) {
    suspend operator fun invoke(): Resource<String> {
        return try {
            val location = locationTracker.getCurrentLocation() ?: throw MissingLocationError()
            val cityName = geoCoding.getCityName(location) ?: throw MissingCityNameError()
            Resource.Success(cityName)
        } catch (e: LocationAndCityNameException) {
            e.printStackTrace() // Log the exception for debugging
            Resource.Error(e.message ?: "An unexpected error occurred")
        }
    }
}
