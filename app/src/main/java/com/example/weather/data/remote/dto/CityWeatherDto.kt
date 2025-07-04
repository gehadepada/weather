package com.example.weather.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityWeatherDto(
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("generationtime_ms")
    val generationTimeMs: Double,
    @SerialName("utc_offset_seconds")
    val utcOffsetSeconds: Int,
    @SerialName("timezone")
    val timezone: String,
    @SerialName("timezone_abbreviation")
    val timezoneAbbreviation: String,
    @SerialName("elevation")
    val elevation: Double,
    @SerialName("current_units")
    val currentUnits: CurrentUnits,
    @SerialName("current")
    val current: Current,
    @SerialName("hourly_units")
    val hourlyUnits: HourlyUnits? = null,
    @SerialName("hourly")
    val hourly: Hourly? = null,
    @SerialName("daily_units")
    val dailyUnits: DailyUnits? = null,
    @SerialName("daily")
    val daily: Daily? = null
)

@Serializable
data class Current(
    @SerialName("time")
    val time: String,
    @SerialName("interval")
    val interval: Int,
    @SerialName("temperature_2m")
    val temperature2m: Double,
    @SerialName("apparent_temperature")
    val apparentTemperature: Double,
    @SerialName("is_day")
    val isDay: Int,
    @SerialName("weather_code")
    val weatherCode: Int,
    @SerialName("relative_humidity_2m")
    val relativeHumidity2m: Double,
    @SerialName("surface_pressure")
    val surfacePressure: Double,
    @SerialName("wind_speed_10m")
    val windSpeed10m: Double,
    @SerialName("rain")
    val rain: Double
)

@Serializable
data class CurrentUnits(
    @SerialName("time")
    val time: String,
    @SerialName("interval")
    val interval: String,
    @SerialName("temperature_2m")
    val temperature2m: String,
    @SerialName("apparent_temperature")
    val apparentTemperature: String,
    @SerialName("is_day")
    val isDay: String,
    @SerialName("weather_code")
    val weatherCode: String,
    @SerialName("relative_humidity_2m")
    val relativeHumidity2m: String,
    @SerialName("surface_pressure")
    val surfacePressure: String,
    @SerialName("wind_speed_10m")
    val windSpeed10m: String,
    @SerialName("rain")
    val rain: String
)

@Serializable
data class Hourly(
    @SerialName("time")
    val time: List<String>,
    @SerialName("temperature_2m")
    val temperature2m: List<Double>,
    @SerialName("weather_code")
    val weatherCode: List<Int>
)

@Serializable
data class HourlyUnits(
    @SerialName("time")
    val time: String,
    @SerialName("temperature_2m")
    val temperature2m: String,
    @SerialName("weather_code")
    val weatherCode: String
)

@Serializable
data class Daily(
    @SerialName("time")
    val time: List<String>,
    @SerialName("temperature_2m_max")
    val temperature2mMax: List<Double>,
    @SerialName("temperature_2m_min")
    val temperature2mMin: List<Double>,
    @SerialName("weather_code")
    val weatherCode: List<Int>
)

@Serializable
data class DailyUnits(
    @SerialName("time")
    val time: String,
    @SerialName("temperature_2m_max")
    val temperature2mMax: String,
    @SerialName("temperature_2m_min")
    val temperature2mMin: String,
    @SerialName("weather_code")
    val weatherCode: String
)