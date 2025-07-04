package com.example.weather.data.remote.location

import CityLocation
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.coroutines.resume

class LocationService(private val context: Context) {

    private val fusedLocationProvider = LocationServices.getFusedLocationProviderClient(context)

    suspend fun getCurrentLocation(): CityLocation? {

        val hasFineLocation = ActivityCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasCoarseLocation = ActivityCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED


        if (!hasFineLocation && !hasCoarseLocation) {
            return null
        }

        return withTimeoutOrNull(15000) { // 15 second timeout
            getCurrentLocationInternal()
        } ?: run {

            getLastKnownLocationFallback()
        }
    }

    private suspend fun getCurrentLocationInternal(): CityLocation? = suspendCancellableCoroutine { continuation ->

        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY, // Use GPS for most accurate location
            10000 // Update interval: 10 seconds
        ).apply {
            setMinUpdateIntervalMillis(5000) // Fastest update: 5 seconds
            setMaxUpdateDelayMillis(15000) // Max delay: 15 seconds
            setMaxUpdates(1) // Only need one location update
        }.build()

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                val location = locationResult.lastLocation
                if (location != null) {
                    fusedLocationProvider.removeLocationUpdates(this)
                    if (continuation.isActive) {
                        continuation.resume(CityLocation(location.latitude, location.longitude))
                    }
                } else {
                    fusedLocationProvider.removeLocationUpdates(this)
                    if (continuation.isActive) {
                        continuation.resume(null)
                    }
                }
            }

            override fun onLocationAvailability(availability: LocationAvailability) {
                if (!availability.isLocationAvailable) {
                    fusedLocationProvider.removeLocationUpdates(this)
                    if (continuation.isActive) {
                        continuation.resume(null)
                    }
                }
            }
        }

        continuation.invokeOnCancellation {

            fusedLocationProvider.removeLocationUpdates(locationCallback)
        }

        try {
            fusedLocationProvider.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        } catch (securityException: SecurityException) {
            if (continuation.isActive) {
                continuation.resume(null)
            }
        }
    }

    private suspend fun getLastKnownLocationFallback(): CityLocation? = suspendCancellableCoroutine { continuation ->


        try {
            fusedLocationProvider.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {

                        continuation.resume(CityLocation(location.latitude, location.longitude))
                    } else {

                        continuation.resume(null)
                    }
                }
                .addOnFailureListener { exception ->
                    continuation.resume(null)
                }
        } catch (securityException: SecurityException) {
            continuation.resume(null)
        }
    }
}