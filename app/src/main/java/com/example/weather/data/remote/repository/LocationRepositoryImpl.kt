package com.example.weather.data.remote.repository

import CityLocation
import com.example.weather.data.remote.location.LocationService
import com.example.weather.logic.repository.LocationRepository


class LocationRepositoryImpl(private val locationService: LocationService) : LocationRepository {
    override suspend fun getCurrentLocation(): CityLocation {
        return locationService.getCurrentLocation()!!
    }
}
