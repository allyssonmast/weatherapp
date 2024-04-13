package com.example.weatherapp.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.usecase.SearchWeatherUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(private val searchWeatherUseCase: SearchWeatherUsecase) : ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set

    private var lastQuery: String? = null

    private fun getCurrentWeather(query: String) {
      viewModelScope.launch {
          state = state.copy(
              isLoading = true,
          )

      }
    }

    fun refreshWeather() {
        lastQuery?.let { query ->
            getCurrentWeather(query)
        }
    }
}