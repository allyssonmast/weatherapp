package com.example.weatherapp.data.geocoder

import android.location.Address
import android.location.Geocoder
import android.location.Location
import java.io.IOException
import javax.inject.Inject

class GeoCoding @Inject constructor(private val geocoder: Geocoder){
    fun getCityName(location: Location): String? {
        try {
            val addresses: List<Address>? = geocoder.getFromLocation(location.latitude, location.longitude, 1)
            if (!addresses.isNullOrEmpty()) {
                return addresses[0].locality
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}
