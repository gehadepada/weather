package com.android.weatherapp.presentation.viewmodel


import android.location.Geocoder
import android.util.Log
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.weatherapp.presentation.state.DayUiState
import com.android.weatherapp.presentation.state.HourUiState
import com.android.weatherapp.presentation.state.WeatherUiState

import com.example.weather.data.remote.repository.mapper.WeatherDescriptionCodeMapper
import com.example.weather.data.remote.repository.mapper.WeatherIconMapper
import com.example.weather.logic.repository.LocationRepository
import com.example.weather.logic.usecase.GetWeatherByLocationUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


class WeatherViewModel(
    private val applicationContext: Context,
    private val locationRepository: LocationRepository,
    private val getWeatherByLocationUseCase: GetWeatherByLocationUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(WeatherUiState())
    val state = _state.asStateFlow()




    private suspend fun getAddressFromLocation(latitude: Double, longitude: Double): String {
        return withContext(Dispatchers.IO) {
            try {
                val geocoder = Geocoder(applicationContext, Locale.getDefault())
                val addresses = geocoder.getFromLocation(latitude, longitude, 1)

                if (addresses?.isNotEmpty() == true) {
                    val address = addresses[0]

                    val cityName = address.locality
                        ?: address.subAdminArea
                        ?: address.adminArea
                        ?: address.countryName
                        ?: "Unknown Location"


                    val governorate = address.adminArea
                    val country = address.countryName

                    when {
                        cityName != null && governorate != null -> "$cityName, $governorate"
                        cityName != null -> cityName
                        governorate != null -> governorate
                        else -> "Unknown Location"
                    }
                } else {
                    "Location not found"
                }
            } catch (e: Exception) {
                Log.e("WeatherViewModel", "Geocoder error: ${e.message}", e)
                "Location unavailable"
            }
        }
    }


    fun getLocationAndWeather() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val location = locationRepository.getCurrentLocation()

                _state.update {
                    it.copy(
                        latitude = location.latitude,
                        longitude = location.longitude,
                        address = "Getting address..."
                    )
                }

                val addressName = getAddressFromLocation(location.latitude, location.longitude)

                _state.update {
                    it.copy(address = "$addressName")
                }
                val cityWeather = getWeatherByLocationUseCase.getWeatherUseCase()
                val Mor = cityWeather.current.isDay
                val dailyForecasts = cityWeather.daily.dailyForecasts.mapIndexed { index, daily ->
                    DayUiState(
                        dayName =  LocalDate.parse(daily.date)
                            .format(DateTimeFormatter.ofPattern("EEEE")),
                        weatherImgId = WeatherIconMapper(daily.weatherCode, Mor),
                        temperatureMax = daily.maxTemperature.toInt(),
                        temperatureMin = daily.minTemperature.toInt()
                    )
                }

                val hourlyForecasts = cityWeather.hourly.hourlyForecasts.map {
                    HourUiState(
                        time = it.time,
                        weatherImgId = WeatherIconMapper(it.weatherCode, Mor),
                        temperature = it.temperature.toInt()
                    )
                }


                _state.update {
                    it.copy(
                        latitude = location.latitude,
                        longitude = location.longitude,
                        address = addressName ,
                        currentTemperature = cityWeather.current.temperature.toInt(),
                        weatherDescription = WeatherDescriptionCodeMapper(cityWeather.current.weatherCode),
                        currentWeatherImgId = WeatherIconMapper(cityWeather.current.weatherCode, 1),
                        dayUiState = dailyForecasts,
                        hourUiState = hourlyForecasts,
                        windSpeed = cityWeather.current.windSpeed.toInt(),
                        humidity = cityWeather.current.humidity.toInt(),
                        pressure = cityWeather.current.pressure.toInt() ,
                        is_day = Mor,
                        apparentTemperature=cityWeather.current.apparentTemperature.toInt(),
                        uv = cityWeather.current.uvIndexMax.toInt(),
                        currentTempMax = cityWeather.current.maxTem.toInt(),
                        currentTempMin = cityWeather.current.minTem.toInt(),


                    )
                }

            } catch (e: Exception) {
                Log.e("WeatherViewModel", "Error getting location and weather: ${e.message}", e)
                _state.update {
                    it.copy(
                        address = "Error: ${e.message}"
                    )
                }
            }
        }
    }

}
