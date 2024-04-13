package com.example.weatherapp.domain.usecase

import android.content.Context
import com.example.weatherapp.data.geocoder.getCityName
import com.example.weatherapp.data.remote.model.Location
import com.example.weatherapp.domain.location.LocationTracker
import javax.inject.Inject

class GetUserLocationAndCityNameUseCase @Inject constructor(
    private val locationTracker: LocationTracker,
    private val context: Context
) {
    suspend operator fun invoke(): Pair<android.location.Location?, String?> {
        val location = locationTracker.getCurrentLocation()
        val cityName = location?.let { getCityName(context, it) }
        return Pair(location, cityName)
    }
}
