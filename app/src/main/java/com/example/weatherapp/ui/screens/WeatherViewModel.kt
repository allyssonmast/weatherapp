package com.example.weatherapp.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.usecase.SearchWeatherUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.weatherapp.domain.usecase.GetConnectivity
import com.example.weatherapp.domain.usecase.GetUserLocationAndCityNameUseCase
import com.example.weatherapp.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val searchWeatherUseCase: SearchWeatherUsecase,
    private val getConnectivity: GetConnectivity,
    private val getUserLocation: GetUserLocationAndCityNameUseCase
    ) : ViewModel() {

    private val _weatherState = MutableStateFlow(WeatherState(isLoading = true))
    val weatherState: StateFlow<WeatherState> = _weatherState

    fun onStartApplication() {
        viewModelScope.launch {
            if (!getConnectivity.isConnected()) {
                _weatherState.value = WeatherState(error = "No internet connection")
                return@launch
            }
            val (location,query) = getUserLocation()
            if(query!=null)
                getWeatherByName(query)
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