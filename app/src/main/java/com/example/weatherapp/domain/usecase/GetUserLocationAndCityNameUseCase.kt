package com.example.weatherapp.domain.usecase

import android.content.Context
import android.location.Location
import com.example.weatherapp.data.geocoder.GeoCoding
import com.example.weatherapp.domain.location.LocationTracker
import javax.inject.Inject

class GetUserLocationAndCityNameUseCase @Inject constructor(
    private val locationTracker: LocationTracker,
    private val geoCoding: GeoCoding,
) {
    suspend operator fun invoke(): String? {
        val location = locationTracker.getCurrentLocation()
        return location?.let { geoCoding.getCityName( it) }
    }
}
