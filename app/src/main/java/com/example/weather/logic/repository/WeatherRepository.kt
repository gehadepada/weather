package com.example.weather.logic.repository

import CityWeather
interface WeatherRepository {
    suspend fun getWeatherByLocation(latitude: Double, longitude: Double) : CityWeather
}