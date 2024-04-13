package com.example.weatherapp.data.geocoder

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import java.io.IOException

fun getCityName(context: Context, location: Location): String? {
    val geocoder = Geocoder(context)
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