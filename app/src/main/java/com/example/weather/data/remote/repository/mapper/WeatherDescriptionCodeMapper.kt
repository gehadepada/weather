package com.example.weather.data.remote.repository.mapper

    fun WeatherDescriptionCodeMapper(weatherCode: Int): String {

        return when (weatherCode) {
            0 -> "Clear sky"

            1 -> "Mainly clear"
            2 -> "partly cloudy"
            3 -> "and overcast"

            45 -> "Fog "
            48 -> "depositing rime fog"

            51 -> "Drizzle: Light"
            53 -> "Drizzle: moderate"
            55 -> "Drizzle: dense intensity"

            56 -> "Freezing Drizzle: Light "
            57 -> "Freezing Drizzle: dense intensity"

            61 -> "Rain: Slight"
            63 -> "Rain: moderate"
            65 -> "Rain: heavy intensity"

            66 -> "Freezing Rain: Light"
            67 -> "Freezing Rain: heavy intensity"

            71 -> "Snow fall: Slight"
            73 -> "Snow fall: moderate"
            75 -> "Snow fall: heavy intensity"

            77 -> "Snow grains"


            80 -> "Rain showers: Slight"
            81 -> "Rain showers: moderate"
            82 -> "Rain showers: violent"

            85 -> "Snow showers slight"
            86 -> "Snow showers heavy"

            95 -> "Thunderstorm: Slight or moderate"

            96 -> "Thunderstorm with slight"
            99 -> "Thunderstorm with heavy hail"
            else -> "Unknown weather condition"

        }
    }