package com.example.weather.presentation.ui.screen

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.weatherapp.presentation.viewmodel.WeatherViewModel
import com.example.weather.R
import com.example.weather.presentation.ui.components.TopTitle
import com.example.weather.presentation.ui.components.sevenDaysForecast
import com.example.weather.presentation.ui.components.HourlyForecast
import com.example.weather.presentation.ui.components.WeatherBox
import com.example.weather.presentation.ui.components.WeatherBoxItem
import com.example.weather.presentation.ui.components.WeatherImage
import com.example.weather.presentation.ui.components.WeatherDetails
import com.example.weather.ui.theme.black100
import com.example.weather.ui.theme.black8
import com.example.weather.ui.theme.urbanistFontFamily
import com.example.weather.ui.theme.white70
import org.koin.androidx.compose.koinViewModel
import kotlin.math.min

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = koinViewModel()) {
    val state  by viewModel.state.collectAsState()

    val weatherBoxData = listOf(
        WeatherBoxItem(R.drawable.elements__1_, "${state.windSpeed} KM/h", "Wind"),
        WeatherBoxItem(R.drawable.humidity, "${state.humidity} %", "Humidity"),
        WeatherBoxItem(R.drawable.rain, "${state.address} %", "Rain"),
        WeatherBoxItem(R.drawable.uv_02, "${state.uv}", "UV Index"),
        WeatherBoxItem(R.drawable.arrow_down_05, "${state.pressure} hPa", "Pressure"),
        WeatherBoxItem(R.drawable.temperature, "${state.apparentTemperature} °C", "Feels Like")
    )

    LaunchedEffect(Unit) {
        viewModel.getLocationAndWeather()
    }

    val DarkBackground = listOf(Color(0xFF060414), Color(0xFF0D0C19))
    var isDay = state.is_day

    val scrollState = rememberScrollState()
    val density = LocalDensity.current

    val scrollProgress = min(scrollState.value / 400f, 1f)

    val animatedScrollProgress by animateFloatAsState(
        targetValue = scrollProgress,
        label = "ScrollProgressAnimation"
    )


    val headerIconScale = 1f - (animatedScrollProgress * 0.4f)
    val headerIconOffsetX = -(animatedScrollProgress * 120f)
    val headerIconOffsetY = animatedScrollProgress * 130f

    val currentWeatherOffsetX = animatedScrollProgress * 100f
    val currentWeatherOffsetY = -(animatedScrollProgress * 80f)
    val contentOffsetY = -(animatedScrollProgress * 80f)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(
                if (isDay != 1) Modifier.background(Brush.linearGradient(DarkBackground))
                else Modifier.background(
                    Brush.linearGradient(
                        listOf(
                            Color(0xFF87CEFA),
                            Color.White
                        )
                    )
                )
            )
            .verticalScroll(scrollState)
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        TopTitle(state.address, isDay = isDay)

        WeatherImage(
            modifier = Modifier,
            image = state.currentWeatherImgId,
            headerIconScale = headerIconScale,
            headerIconOffsetX = headerIconOffsetX,
            headerIconOffsetY = headerIconOffsetY,
        )

        WeatherDetails(
            modifier = Modifier,
            temperature = state.currentTemperature,
            description = state.weatherDescription,
            maxTemperature = state.currentTempMax,
            minTemperature = state.currentTempMin,
            isDay = isDay,
            currentWeatherOffsetX = currentWeatherOffsetX,
            currentWeatherOffsetY = currentWeatherOffsetY,
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .height(270.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 24.dp),
            userScrollEnabled = false
        ) {
            items(weatherBoxData) { item ->
                WeatherBox(
                    modifier = Modifier,
                    icon = painterResource(id = item.icon),
                    textInfo1 = item.text,
                    textInfo2 = item.text2,
                    isDay = isDay
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Today",
            fontSize = 20.sp,
            fontFamily = urbanistFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = if (isDay != 1) Color(0xFFFFFFFF) else black100,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp).padding(bottom = 30.dp)
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(state.hourUiState) { hourlyForecast ->
                HourlyForecast(
                    modifier = Modifier.width(80.dp),
                    icon = hourlyForecast.weatherImgId,
                    text1 = hourlyForecast.temperature,
                    text2 = hourlyForecast.time,
                    isDay = isDay
                )
            }
        }

        Text(
            text = "Next 7 days",
            fontSize = 20.sp,
            fontFamily = urbanistFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = if ((isDay != 1)) Color(0xFFFFFFFF) else black100,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp).padding( bottom = 12.dp).padding( top = 16.dp)
        )

        Box(
            modifier = Modifier
                .width(346.dp)
                .height(435.dp)
                .background(
                    color = if ((isDay != 1)) Color(0xB3060414)else white70,
                    shape = RoundedCornerShape(24.dp)
                )
                .drawBehind {
                    drawLine(
                        color = black8,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = 1f
                    )
                }
                .padding(vertical = 8.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                state.dayUiState.forEach {
                    sevenDaysForecast(
                        day = it.dayName,
                        icon = it.weatherImgId,
                        max = it.temperatureMax,
                        min = it.temperatureMin,
                        isDay = isDay
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}