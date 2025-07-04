package com.example.weather.logic.repository

import CityLocation

interface LocationRepository {
    suspend fun getCurrentLocation() : CityLocation
}