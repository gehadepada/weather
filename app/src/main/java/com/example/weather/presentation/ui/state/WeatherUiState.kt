package com.android.weatherapp.presentation.state

import com.example.weather.R


data class WeatherUiState(

    val address: String="",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val weatherDescription: String="",
    val currentTempMax: Int=0,
    val currentTempMin: Int=0,
    val currentWeatherImgId: Int= R.drawable.location_icon,
    val currentTemperature:Int =0,

    val windSpeed: Int =0,
    val humidity:Int =0,
    val rain:Int =0,
    val uv: Int=0,
    val pressure: Int=0,
    val temperature: Int=0,
    val apparentTemperature :Int =0,
    val is_day: Int=0,

    val hourUiState: List<HourUiState> = listOf(),
    val dayUiState: List<DayUiState> = listOf(),
    val temperatureMax: Int=0,
    val temperatureMin: Int =0,
)


data class HourUiState(
    val weatherImgId: Int=R.drawable.location_icon,
    val temperature: Int =0,
    val time: String=""

)

data class DayUiState(
    val temperatureMax: Int =0,
    val temperatureMin: Int =0,
    val weatherImgId: Int=R.drawable.location_icon,
    val dayName: String=""
)