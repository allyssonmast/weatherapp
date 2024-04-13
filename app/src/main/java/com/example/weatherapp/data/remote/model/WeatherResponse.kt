package com.example.weatherapp.data.remote.model

import com.example.weatherapp.domain.model.Weather
import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("request")
    val request: Request,
    @SerializedName("location")
    val location: Location,
    @SerializedName("current")
    val current: CurrentWeather
)

data class Request(
    @SerializedName("type")
    val type: String,
    @SerializedName("query")
    val query: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("unit")
    val unit: String
)

data class Location(
    @SerializedName("name")
    val name: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("lat")
    val lat: String,
    @SerializedName("lon")
    val lon: String,
    @SerializedName("timezone_id")
    val timezoneId: String,
    @SerializedName("localtime")
    val localtime: String,
    @SerializedName("localtime_epoch")
    val localtimeEpoch: Number,
    @SerializedName("utc_offset")
    val utcOffset: String
)

data class CurrentWeather(
    @SerializedName("observation_time")
    val observationTime: String,
    @SerializedName("temperature")
    val temperature: Number,
    @SerializedName("weather_code")
    val weatherCode: Number,
    @SerializedName("weather_icons")
    val weatherIcons: List<String>,
    @SerializedName("weather_descriptions")
    val weatherDescriptions: List<String>,
    @SerializedName("wind_speed")
    val windSpeed: Number,
    @SerializedName("wind_degree")
    val windDegree: Number,
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("pressure")
    val pressure: Number,
    @SerializedName("precip")
    val precip: Number,
    @SerializedName("humidity")
    val humidity: Number,
    @SerializedName("cloudcover")
    val cloudCover: Number,
    @SerializedName("feelslike")
    val feelsLike: Number,
    @SerializedName("uv_index")
    val uvIndex: Number,
    @SerializedName("visibility")
    val visibility: Number
)

fun mapWeatherResponseToWeather(weatherResponse: WeatherResponse): Weather {
    val currentWeather = weatherResponse.current
    return Weather(
        iconUrl = currentWeather.weatherIcons.firstOrNull() ?: "",
        temperature = currentWeather.temperature,
        weatherDescription = currentWeather.weatherDescriptions.firstOrNull() ?: "",
        windSpeed = currentWeather.windSpeed,
        feelsLike = currentWeather.feelsLike,
        humidity = currentWeather.humidity
    )
}

