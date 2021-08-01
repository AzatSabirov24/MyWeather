package com.azat_sabirov.myweather.utils

import com.azat_sabirov.myweather.model.FactDTO
import com.azat_sabirov.myweather.model.Weather
import com.azat_sabirov.myweather.model.WeatherDTO
import com.azat_sabirov.myweather.model.getDefaultCity

fun convertDtoToModel(weatherDTO: WeatherDTO): List<Weather> {
   val fact: FactDTO = weatherDTO.fact!!
   return listOf(Weather(getDefaultCity(), fact.temp!!, fact.feels_like!!, fact.condition!!, fact.icon))
}
