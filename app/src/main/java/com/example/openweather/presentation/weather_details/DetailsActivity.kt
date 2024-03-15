package com.example.openweather.presentation.weather_details

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.openweather.data.NetworkModule
import com.example.openweather.data.WeatherRepository
import com.example.openweather.databinding.ActivityDetailsBinding
import com.example.openweather.domain.GetWeatherDetailsUseCase
import com.example.openweather.utils.Constants
import kotlinx.coroutines.launch

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val weatherRepository = WeatherRepository(NetworkModule.weatherService)
        val getWeatherDetailsUseCase = GetWeatherDetailsUseCase(weatherRepository)
        viewModel = DetailsViewModel(getWeatherDetailsUseCase)

        setupObserver()
        val cityName = intent.getStringExtra(Constants.KEY_CITY_NAME)
        if (cityName != null) {
            viewModel.getWeatherDetails(cityName)
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.weatherDetails.collect { weatherDetails ->
                    weatherDetails.let { data ->
                        "${data?.temperature}°".also { binding.tvCityTemp.text = it }
                        binding.tvLongValue.text = data?.long
                        binding.tvLatValue.text = data?.lat
                        binding.tvCityTempMain.text = data?.tempMain
                        binding.tvCityTempDescription.text = data?.weatherDescription
                        binding.tvCountryValue.text = data?.country
                        binding.tvCityName.text = data?.cityName
                        binding.tvMinTempValue.text = data?.tempMin
                        binding.tvMaxTempValue.text = data?.tempMax
                        binding.tvPressureValue.text = data?.pressure.toString()
                        binding.tvHumidityValue.text = data?.humidity.toString()
                        setViewVisible()
                    }
                }
            }
        }
    }

    private fun setViewVisible() {
        val views = arrayOf(
            binding.tvCountry,
            binding.tvLong,
            binding.tvLat,
            binding.tvPressure,
            binding.tvHumidity,
            binding.tvMaxTemp,
            binding.tvMinTemp
        )
        views.forEach { it.isVisible = true }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

