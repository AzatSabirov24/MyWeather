package com.azat_sabirov.myweather.viewModel

import com.azat_sabirov.myweather.model.Weather

sealed class AppState {
   data class Success(val weatherData: Weather) : AppState()
   data class Error(val error: Throwable) : AppState()
   object Loading : AppState()
}