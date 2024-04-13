package com.example.weatherapp.ui.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.usecase.SearchWeatherUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.weatherapp.domain.usecase.GetConnectivity
import com.example.weatherapp.domain.usecase.GetUserLocationAndCityNameUseCase
import com.example.weatherapp.util.Resource
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val searchWeatherUseCase: SearchWeatherUsecase,
    private val getConnectivity: GetConnectivity,
    private val getUserLocation: GetUserLocationAndCityNameUseCase
    ) : ViewModel() {

    private val _weatherState = mutableStateOf(WeatherState(isLoading = true))
    val weatherState: State<WeatherState> = _weatherState

    init {
        onStartApplication()
        print("iniciou")
    }
    private fun onStartApplication() {
        viewModelScope.launch {
            val isConnected = getConnectivity.isConnected()
            if (!isConnected) {
                _weatherState.value = WeatherState(error = "No internet connection")
                return@launch
            }
            val query = getUserLocation()
            val que= query

        }
    }
    fun getWeatherByName (query: String){
        searchWeatherUseCase(query).onEach { resource ->
            when(resource){
                is Resource.Success->{
                    _weatherState.value = WeatherState(weather = resource.data, isLoading = false)
                }is Resource.Error->{
                _weatherState.value = WeatherState(error = resource.message ?: "Unknown error",isLoading = false)
            }is Resource.Loading ->{
                _weatherState.value = WeatherState(isLoading = true)
            }
            }
        }
    }
}