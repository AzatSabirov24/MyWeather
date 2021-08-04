package com.azat_sabirov.myweather.repository

import com.azat_sabirov.myweather.model.Weather

interface LocalRepository {
    fun getAllHistory(): List<Weather>
    fun saveEntity(weather: Weather)
}