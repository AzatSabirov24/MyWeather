package com.azat_sabirov.myweather.repository

import com.azat_sabirov.myweather.model.WeatherDTO
import javax.security.auth.callback.Callback

interface DetailsRepository {
   fun getWeatherDetailsFromServer(
       lat: Double,
       lon: Double,
       callback: retrofit2.Callback<WeatherDTO>
   )
}