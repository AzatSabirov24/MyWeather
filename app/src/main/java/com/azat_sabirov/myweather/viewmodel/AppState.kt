package com.azat_sabirov.myweather.viewmodel

import com.azat_sabirov.myweather.model.Weather

sealed class AppState {

    data class Succes(val weatherData: List<Weather>) : AppState()
    object Loading : AppState()
    data class Error(val error: Throwable) : AppState()

}