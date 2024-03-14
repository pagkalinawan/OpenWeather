package com.example.openweather.domain

data class WeatherDetailsModel(
    val cityName: String,
    val temperature: String,
    val humidity: Int,
    val pressure: Int,
    val weatherDescription: String,
    val tempMain: String,
    val country: String,
    val long: String,
    val lat: String,
    val tempMin: String,
    val tempMax: String
)