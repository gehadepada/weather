package com.example.weather.data.remote.repository.mapper

import CityLocation
import CityWeather
import CurrentWeather
import DailyForecast
import DailyWeather
import HourlyForecast
import HourlyWeather
import com.example.weather.data.remote.dto.CityWeatherDto

fun CityWeatherDto.toCityWeather(): CityWeather {
    val currentWeather = CurrentWeather(
        temperature = current.temperature2m,
        apparentTemperature = current.apparentTemperature,
        weatherCode = current.weatherCode,
        isDay = current.isDay,
        humidity = current.relativeHumidity2m,
        pressure = current.surfacePressure,
        windSpeed = current.windSpeed10m,
        rain = current.rain,
        uvIndexMax =0.0,
        time = current.time,
        maxTem=daily!!.temperature2mMax[0],
        minTem=daily!!.temperature2mMin[0],



    )

    val hourlyWeather = hourly?.let {
        val times = it.time
        val temps = it.temperature2m
        val codes = it.weatherCode

        times.indices.mapNotNull { i ->
            val time = times.getOrNull(i) ?: return@mapNotNull null
            val temp = temps.getOrNull(i) ?: return@mapNotNull null
            val code = codes.getOrNull(i) ?: return@mapNotNull null

            HourlyForecast(
                weatherCode = code,
                temperature = temp,
                time = time.substringAfter("T")
            )
        }
    } ?: emptyList()

    val dailyWeather = daily?.let {
        val dates = it.time
        val maxTemps = it.temperature2mMax
        val minTemps = it.temperature2mMin
        val codes = it.weatherCode

        dates.indices.mapNotNull { i ->
            val date = dates.getOrNull(i) ?: return@mapNotNull null
            val maxTemp = maxTemps.getOrNull(i) ?: return@mapNotNull null
            val minTemp = minTemps.getOrNull(i) ?: return@mapNotNull null
            val code = codes.getOrNull(i) ?: return@mapNotNull null

            DailyForecast(
                weatherCode = code,
                maxTemperature = maxTemp,
                minTemperature = minTemp,
                date = date
            )
        }
    } ?: emptyList()

    return CityWeather(
        location = CityLocation(
            latitude = latitude,
            longitude = longitude,
        ),
        current = currentWeather,
        hourly = HourlyWeather(hourlyWeather),
        daily = DailyWeather(dailyWeather)
    )
}