package com.example.weatherapp.util.exceptions

sealed class LocationAndCityNameException(message: String) : Exception(message)

class MissingLocationError : LocationAndCityNameException("Failed to retrieve user location")

class MissingCityNameError : LocationAndCityNameException("Failed to retrieve city name")
