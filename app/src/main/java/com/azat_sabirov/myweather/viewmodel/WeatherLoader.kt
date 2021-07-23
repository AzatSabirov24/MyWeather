package com.azat_sabirov.myweather.viewmodel

import android.os.Build
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import com.azat_sabirov.myweather.BuildConfig
import com.azat_sabirov.myweather.model.City
import com.google.gson.Gson
import com.azat_sabirov.myweather.model.WeatherDTO
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

@RequiresApi(Build.VERSION_CODES.N)
class WeatherLoader(
    private val listener: WeatherLoaderListener,
    private val lat: Double,
    private val lon: Double
) {

    @RequiresApi(Build.VERSION_CODES.N)
    internal fun loadWeather() {
        try {
            val uri =
                URL("https://api.weather.yandex.ru/v2/informers?lat=${lat}&lon=${lon}")
            @Suppress("DEPRECATION") val handler = Handler()
            Thread {
                lateinit var urlConnection: HttpsURLConnection
                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.addRequestProperty(
                        "X-Yandex-API-Key",
                        BuildConfig.WEATHER_API_KEY
                    )
                    urlConnection.readTimeout = 10000
                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))
                    val weatherDTO: WeatherDTO =
                        Gson().fromJson(getLines(bufferedReader), WeatherDTO::class.java)
                    handler.post { listener.onLoaded(weatherDTO) }
                } catch (e: Exception) {
                    Log.e("", "Fail connection", e)
                    e.printStackTrace()
                } finally {
                    urlConnection.disconnect()
                }
            }.start()
        } catch (e: MalformedURLException) {
            Log.e("", "Fail URI", e)
            e.printStackTrace()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

    interface WeatherLoaderListener {
        fun onLoaded(weatherDTO: WeatherDTO)
        fun onFailed(throwable: Throwable)
    }
}
