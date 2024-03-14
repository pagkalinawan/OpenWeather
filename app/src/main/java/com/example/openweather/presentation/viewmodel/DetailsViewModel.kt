package com.example.openweather.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openweather.domain.WeatherDetailsModel
import com.example.openweather.domain.GetWeatherDetailsUseCase
import kotlinx.coroutines.launch

//class DetailsViewModel: ViewModel() {
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
//            service.getWeatherByCityName(cityName, "99281cbd7c0f1372ae8afa3aa8b10fe0", "metric")
//        _weather.value = response
//    }

//    private val _weather = MutableLiveData<WeatherResponse>()
//    val weatherResponseLiveData: LiveData<WeatherResponse> = _weather
//
//    fun getForecast(cityName: String) = viewModelScope.launch {
//        val response = NetworkModule.weatherService.getWeatherByCityName(
//            cityName,
//            "99281cbd7c0f1372ae8afa3aa8b10fe0",
//            "metric"
//        )
//        _weather.value = response
//    }


class DetailsViewModel(private val getWeatherDetailsUseCase: GetWeatherDetailsUseCase) :
    ViewModel() {
    private val _weatherDetails = MutableLiveData<WeatherDetailsModel>()
    val weatherDetails: LiveData<WeatherDetailsModel> = _weatherDetails

    fun getWeatherDetails(cityName: String) {
        viewModelScope.launch {
            val details = getWeatherDetailsUseCase(cityName)
            _weatherDetails.value = details
        }
    }
}

//}
