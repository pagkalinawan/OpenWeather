package com.example.openweather.data

import com.example.openweather.data.model.ForecastResponse
import com.example.openweather.data.model.WeatherResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

interface WeatherService {

    @GET("forecast")
    suspend fun getForecastByCityName (
        @Query("q") cityName: String,
        @Query("appid") appId: String
    ): ForecastResponse

    @GET("weather")
    suspend fun getWeatherByCityName (
        @Query("q") cityName: String,
        @Query("appid") appId: String,
        @Query("units") units: String
    ): WeatherResponse

}