package com.example.weather.logic.usecase

import CityWeather
import android.util.Log

import com.example.weather.logic.repository.LocationRepository
import com.example.weather.logic.repository.WeatherRepository

class GetWeatherByLocationUseCase(
    private val weatherRepository: WeatherRepository,
    private val locationRepository: LocationRepository
) {
    suspend fun getWeatherUseCase(): CityWeather {
        val location = locationRepository.getCurrentLocation()

        val weather = weatherRepository.getWeatherByLocation(location.latitude, location.longitude)

        return weather
    }
}
