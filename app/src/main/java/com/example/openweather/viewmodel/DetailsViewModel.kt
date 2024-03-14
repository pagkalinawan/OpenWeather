package com.example.openweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openweather.model.WeatherResponse
import com.example.openweather.utils.WeatherService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class DetailsViewModel: ViewModel() {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val httpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()
    private val service = retrofit.create(WeatherService::class.java)
    private val _weather = MutableLiveData<WeatherResponse>()
    val weatherResponseLiveData: LiveData<WeatherResponse> = _weather

    fun getForecast(cityName: String) = viewModelScope.launch {
        val response =
            service.getWeatherByCityName(cityName, "99281cbd7c0f1372ae8afa3aa8b10fe0", "metric")
        _weather.value = response
    }
}


//class DetailsViewModel @Inject constructor(private val weatherService: WeatherService) :
//    ViewModel() {
//    private val loggingInterceptor = HttpLoggingInterceptor().apply {
//        level = HttpLoggingInterceptor.Level.BODY
//    }
//    private val httpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
//    private val retrofit = Retrofit.Builder()
//        .baseUrl("https://api.openweathermap.org/data/2.5/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .client(httpClient)
//        .build()
//    private val service = retrofit.create(WeatherService::class.java)
//    private val _weather = MutableLiveData<WeatherResponse>()
//    val weatherResponseLiveData: LiveData<WeatherResponse> = _weather
//
//    fun getForecast(cityName: String) = viewModelScope.launch {
//        val response =
//            weatherService.getWeatherByCityName(cityName, "99281cbd7c0f1372ae8afa3aa8b10fe0", "metric")
//        _weather.value = response
//    }
//}