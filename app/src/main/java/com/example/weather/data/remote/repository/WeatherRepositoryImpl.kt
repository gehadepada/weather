package com.example.weather.data.remote.repository

import CityWeather
import android.util.Log
import com.example.weather.data.remote.dto.CityWeatherDto
import com.example.weather.data.remote.repository.mapper.toCityWeather
import com.example.weather.logic.repository.WeatherRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText

class WeatherRepositoryImpl(private val httpClient: HttpClient) : WeatherRepository {

    override suspend fun getWeatherByLocation(latitude: Double, longitude: Double): CityWeather {
      //  Log.d("WeatherRepository", "Fetching weather for lat: $latitude, lon: $longitude")

        try {
            val response = httpClient.get("https://api.open-meteo.com/v1/forecast") {
                parameter("latitude", latitude)
                parameter("longitude", longitude)
                parameter("timezone", "auto")
                parameter("daily", "weather_code,temperature_2m_max,temperature_2m_min")
                parameter("hourly", "temperature_2m,weather_code")
                parameter("forecast", "9")
                parameter("current", "temperature_2m,relative_humidity_2m,apparent_temperature,is_day,rain,weather_code,surface_pressure,wind_speed_10m")
            }

            val jsonString = response.bodyAsText()

            val responseDto = response.body<CityWeatherDto>()

            return responseDto.toCityWeather()

        } catch (e: Exception) {
            Log.e("WeatherRepository", "Error fetching weather: ${e.message}", e)
            throw e
        }
    }
}