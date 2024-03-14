package com.example.openweather.data

import android.util.Log
import com.example.openweather.domain.WeatherDetailsModel
import com.example.openweather.data.model.WeatherResponse
import java.net.UnknownHostException

class WeatherRepository(private val weatherService: WeatherService) {
    suspend fun getWeatherDetails(cityName: String): WeatherDetailsModel {
        return try {
            val weatherResponse = weatherService.getWeatherByCityName(
                cityName,
                "99281cbd7c0f1372ae8afa3aa8b10fe0",
                "metric"
            )
            mapResponseToWeatherDetails(weatherResponse)
        } catch (e: UnknownHostException) {
            Log.e("WeatherRepository", "UnknownHostException: ${e.message}")
            throw e
        }
    }

    private fun mapResponseToWeatherDetails(weatherResponse: WeatherResponse): WeatherDetailsModel {
        val main = weatherResponse.main
        val sys = weatherResponse.sys
        val coord = weatherResponse.coord
        val weatherDescription = weatherResponse.weather.firstOrNull()?.description ?: ""
        val tempMain = weatherResponse.weather.firstOrNull()?.main ?: ""

        return WeatherDetailsModel(
            cityName = weatherResponse.name,
            temperature = main.temp.toString(),
            humidity = main.humidity,
            pressure = main.pressure,
            weatherDescription = weatherDescription,
            tempMain = tempMain,
            country = sys.country,
            long = coord.lon.toString(),
            lat = coord.lat.toString(),
            tempMin = main.temp_min.toString(),
            tempMax = main.temp_max.toString()
        )
    }
}