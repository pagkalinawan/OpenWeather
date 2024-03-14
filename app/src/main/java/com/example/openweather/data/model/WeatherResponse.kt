package com.example.openweather.data.model

data class WeatherResponse(
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val dt: Long,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int,
    val sys: Sys,
    val coord: Coord
)

data class Main(
    val temp: Double,
    val pressure: Int,
    val humidity: Int,
    val temp_min: Double,
    val temp_max: Double
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class Sys(
    val country: String,
    val sunrise: Long,
    val sunset: Long
)

data class Coord(
    val lon: Double,
    val lat: Double
)