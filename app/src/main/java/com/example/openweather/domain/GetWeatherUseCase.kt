package com.example.openweather.domain

import com.example.openweather.data.WeatherRepository

class GetWeatherDetailsUseCase(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(cityName: String): WeatherDetailsModel {
        return weatherRepository.getWeatherDetails(cityName)
    }
}
