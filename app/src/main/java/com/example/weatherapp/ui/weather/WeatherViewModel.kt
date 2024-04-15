package com.example.weatherapp.ui.weather

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.usecase.SearchWeatherUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.weatherapp.domain.usecase.GetUserLocationAndCityNameUseCase
import com.example.weatherapp.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val searchWeatherUseCase: SearchWeatherUsecase,
    private val getUserLocation: GetUserLocationAndCityNameUseCase
) : ViewModel() {

    private val _weatherState = mutableStateOf(WeatherState(isLoading = true))
    val weatherState: State<WeatherState> = _weatherState

    init {
        onStartApplication()
    }

    private fun onStartApplication() {
        viewModelScope.launch {
            getWeatherByUserLocation()
        }
    }

    private suspend fun getWeatherByUserLocation() {
        val cityNameResource = getUserLocation()
        if (cityNameResource is Resource.Success) {
            getWeatherByName(cityNameResource.data)
        } else {
            _weatherState.value = WeatherState(
                error = cityNameResource.message,
                isLoading = false,
            )
        }
    }

    suspend fun getWeatherByName(query: String?) {
        WeatherState(isLoading = true)
        when (val resource = searchWeatherUseCase(query)) {
            is Resource.Success -> {
                _weatherState.value =
                    WeatherState(weather = resource.data, isLoading = false, isConnected = true)
            }

            is Resource.InternetConnection -> {
                _weatherState.value =
                    WeatherState(isLoading = false, isConnected = false)
            }

            is Resource.Error -> {
                _weatherState.value =
                    WeatherState(
                        error = resource.message ?: "Unknown error",
                        isLoading = false,
                        isConnected = true
                    )
            }

            is Resource.Loading -> {
                _weatherState.value = WeatherState(isLoading = true)
            }
        }
    }
}