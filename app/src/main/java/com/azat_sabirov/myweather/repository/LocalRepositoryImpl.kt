package com.azat_sabirov.myweather.repository

import com.azat_sabirov.myweather.history.HistoryDao
import com.azat_sabirov.myweather.model.Weather
import com.azat_sabirov.myweather.utils.convertHistoryEntityToWeather
import com.azat_sabirov.myweather.utils.convertWeatherToEntity

class LocalRepositoryImpl(private val localDataSource: HistoryDao) :
   LocalRepository {

   override fun getAllHistory(): List<Weather> {
       return convertHistoryEntityToWeather(localDataSource.all())
   }

   override fun saveEntity(weather: Weather) {
       localDataSource.insert(convertWeatherToEntity(weather))
   }
}