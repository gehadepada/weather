data class CityWeather(
    val location: CityLocation,
    val current: CurrentWeather,
    val hourly: HourlyWeather,
    val daily: DailyWeather
)

data class CityLocation(
    val latitude: Double,
    val longitude: Double,
)

data class CurrentWeather(
    val temperature: Double,
    val apparentTemperature: Double,
    val weatherCode: Int,
    val isDay: Int,
    val humidity: Double,
    val pressure: Double,
    val windSpeed: Double,
    val rain: Double,
    val uvIndexMax: Double,
    val time: String,
    val maxTem :Double,
    val minTem :Double,

)

data class HourlyWeather(
    val hourlyForecasts: List<HourlyForecast>
)

data class HourlyForecast(
    val weatherCode: Int,
    val temperature: Double,
    val time: String
)

data class DailyWeather(
    val dailyForecasts: List<DailyForecast>
)

data class DailyForecast(
    val weatherCode: Int,
    val maxTemperature: Double,
    val minTemperature: Double,
    val date: String
)
