package com.example.openweather.view

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.openweather.databinding.ActivityDetailsBinding
import com.example.openweather.utils.Constants
import com.example.openweather.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val cityName = intent.getStringExtra(Constants.KEY_CITY_NAME)
        if (cityName != null) {
            viewModel.getForecast(cityName)
        }

        setupObserver()
    }

    private fun setupObserver() {
        viewModel.weatherResponseLiveData.observe(this) { response ->
            "${response.main.temp}°".also { binding.tvCityTemp.text = it }
            binding.tvLongValue.text = response.coord.lon.toString()
            binding.tvLatValue.text = response.coord.lat.toString()
            binding.tvCountryValue.text = response.sys.country
            binding.tvCityName.text = response.name

            response.weather.first().let {
                binding.tvCityTempMain.text = it.main
                binding.tvCityTempDescription.text = it.description
            }

            binding.tvMinTempValue.text = response.main.temp_min.toString()
            binding.tvMaxTempValue.text = response.main.temp_max.toString()
            binding.tvPressureValue.text = response.main.pressure.toString()
            binding.tvHumidityValue.text = response.main.humidity.toString()
            setViewVisible()
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

