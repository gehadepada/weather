
package com.yourpackage.di
import com.example.weather.data.remote.location.LocationService
import com.example.weather.data.remote.repository.LocationRepositoryImpl
import com.example.weather.data.remote.repository.WeatherRepositoryImpl
import com.example.weather.logic.repository.LocationRepository
import com.example.weather.logic.repository.WeatherRepository
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import io.ktor.client.engine.android.Android
import com.example.weather.logic.usecase.GetWeatherByLocationUseCase
import org.koin.androidx.viewmodel.dsl.viewModelOf
import com.android.weatherapp.presentation.viewmodel.WeatherViewModel // Add this import

val appModule = module {

    single { LocationService(get()) }

    single<LocationRepository> { LocationRepositoryImpl(get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }

    single { GetWeatherByLocationUseCase(get(), get()) }

    single {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }
viewModelOf(::WeatherViewModel)

}