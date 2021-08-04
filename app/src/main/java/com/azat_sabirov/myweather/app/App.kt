package com.azat_sabirov.myweather.app

import android.app.Application
import androidx.room.Room
import com.azat_sabirov.myweather.history.HistoryDao
import com.azat_sabirov.myweather.history.HistoryDataBase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {

        private var appInstance: App? = null
        private const val DB_NAME = "History.db"

        private val appHistoryDao by lazy {
            Room
                .databaseBuilder(
                    appInstance!!.applicationContext,
                    HistoryDataBase::class.java,
                    DB_NAME
                )
                .allowMainThreadQueries()
                .build()
                .historyDao()
        }

        fun getHistoryDao(): HistoryDao = appHistoryDao
    }
}