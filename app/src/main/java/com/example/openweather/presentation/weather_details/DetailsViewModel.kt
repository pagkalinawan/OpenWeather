package com.example.openweather.presentation.weather_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openweather.domain.WeatherDetailsModel
import com.example.openweather.domain.GetWeatherDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(private val getWeatherDetailsUseCase: GetWeatherDetailsUseCase) :
    ViewModel() {
//    private val _weatherDetails = MutableLiveData<WeatherDetailsModel>()
//    val weatherDetails: LiveData<WeatherDetailsModel> = _weatherDetails
    private val _weatherDetails = MutableStateFlow<WeatherDetailsModel?>(null)
    val weatherDetails: StateFlow<WeatherDetailsModel?> = _weatherDetails

    fun getWeatherDetails(cityName: String) {
        viewModelScope.launch {
            val details = getWeatherDetailsUseCase(cityName)
            _weatherDetails.value = details
        }
    }


}