package com.example.weather.data.remote.repository.mapper

import com.example.weather.R

fun WeatherIconMapper(code: Int, isDay: Int): Int {

    return when (code) {
        0 -> if (isDay == 1) R.drawable.light_clear_sky else R.drawable.night_clear_sky
        1, 2 -> if (isDay == 1) R.drawable.light_mainly_clear else R.drawable.night_mainly_clear
        3 -> if (isDay == 1) R.drawable.light_overcast else R.drawable.night_overcast

        45 -> if (isDay == 1) R.drawable.light_fog else R.drawable.night_fog
        48 -> if (isDay == 1) R.drawable.light_rime_fog else R.drawable.night_rime_fog

        51 -> if (isDay == 1) R.drawable.light_drizzle_light else R.drawable.night_drizzle_light
        53 -> if (isDay == 1) R.drawable.light_drizzle_moderate else R.drawable.night_drizzle_moderate
        55 -> if (isDay == 1) R.drawable.light_drizzle_intensity else R.drawable.night_drizzle_intensity

        56 -> if (isDay == 1) R.drawable.light_freezing_drizzle_light else R.drawable.night_freezing_drizzle_light
        57 -> if (isDay == 1) R.drawable.light_freezing_drizzle_intensity else R.drawable.night_freezing_drizzle_intensity

        61 -> if (isDay == 1) R.drawable.light_rain_slight else R.drawable.night_rain_slight
        63 -> if (isDay == 1) R.drawable.light_rain_moderate else R.drawable.night_rain_moderate
        65 -> if (isDay == 1) R.drawable.light_rain_intensity else R.drawable.night_rain_intensity

        66 -> if (isDay == 1) R.drawable.light_freezing_rain_light else R.drawable.night_freezing_rain_light
        67 -> if (isDay == 1) R.drawable.light_freezing_rain_heavy else R.drawable.night_freezing_rain_heavy

        71 -> if (isDay == 1) R.drawable.light_snow_fall_light else R.drawable.night_snowfall_slight
        73 -> if (isDay == 1) R.drawable.light_snow_fall_moderate else R.drawable.night_snowfall_moderate
        75 -> if (isDay == 1) R.drawable.light_snowfall_heavy else R.drawable.night_snowfall_heavy

        77 -> if (isDay == 1) R.drawable.light_snow_grains else R.drawable.night_snow_grains

        80 -> if (isDay == 1) R.drawable.light_rain_shower_slight else R.drawable.night_rain_shower_slight
        81 -> if (isDay == 1) R.drawable.light_rain_shower_moderate else R.drawable.night_rain_shower_moderate
        82 -> if (isDay == 1) R.drawable.light_rain_shower_violent else R.drawable.night_rain_shower_violent

        85 -> if (isDay == 1) R.drawable.light_snow_shower_slight else R.drawable.night_snow_shower_slight
        86 -> if (isDay == 1) R.drawable.light_snow_shower_heavy else R.drawable.night_snow_shower_heavy

        95 -> if (isDay == 1) R.drawable.light_thunderstorm_moderate else R.drawable.night_thunderstorm_moderate
        96 -> if (isDay == 1) R.drawable.light_thunderstorm_with_slight_hail else R.drawable.night_thunderstorm_with_slight_hail
        99 -> if (isDay == 1) R.drawable.light_thunderstrom_with_heavy_hail else R.drawable.night_thunderstrom_with_heavy_hail

        else -> if (isDay == 1) R.drawable.light_clear_sky else R.drawable.night_clear_sky
    }
}


