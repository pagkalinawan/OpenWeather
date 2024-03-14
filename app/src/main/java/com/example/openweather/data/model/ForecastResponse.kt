package com.example.openweather.data.model

data class ForecastResponse(
    val cnt: Int,
    val list: List<WeatherData>,
)

data class WeatherData(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>,
    val visibility: Int,
)