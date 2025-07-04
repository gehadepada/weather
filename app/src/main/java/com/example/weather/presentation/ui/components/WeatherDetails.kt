package com.example.weather.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.ui.theme.black24
import com.example.weather.ui.theme.black60
import com.example.weather.ui.theme.black8
import com.example.weather.ui.theme.urbanistFontFamily

@Composable
fun WeatherDetails(
    modifier: Modifier = Modifier,
    temperature: Int,
    description: String,
    maxTemperature: Int,
    minTemperature: Int,
    isDay: Int,
    currentWeatherOffsetX: Float,
    currentWeatherOffsetY: Float,
) {
    // ADD: Get density for graphicsLayer calculations
    val density = LocalDensity.current

    Column(
        modifier = modifier
            .padding(top = 12.dp)
            .graphicsLayer {
                translationX = with(density) { currentWeatherOffsetX.dp.toPx() }
                translationY = with(density) { currentWeatherOffsetY.dp.toPx() }
            },
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$temperature°C",
            fontSize = 64.sp,
            fontFamily = urbanistFontFamily,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.25.sp,
            color = if (isDay != 1) Color(0xFFFFFFFF) else Color(0xFF060414)
        )

        Text(
            text = description,
            fontSize = 12.sp,
            fontFamily = urbanistFontFamily,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.25.sp,
            textAlign = TextAlign.Center,
            color = if (isDay == 0) Color(0x99FFFFFF)
            else Color(0x99060414),
        )

        Button(
            onClick = {},
            colors =if(isDay==1) ButtonDefaults.buttonColors(containerColor = black8) else
                ButtonDefaults.buttonColors(containerColor = Color(0x14FFFFFF)),
            shape = RoundedCornerShape(24.dp),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
            modifier = Modifier
                .width(168.dp)
                .height(35.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MaxMinTemberture(
                    temperature = maxTemperature,
                    icon = painterResource(id = R.drawable.arrow_down_04),
                    isDay = isDay
                )

                Text(
                    text = "|",
                    fontSize = 14.sp,
                    fontFamily = urbanistFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = black24,
                )
                MaxMinTemberture(
                    temperature = minTemperature,
                    icon = painterResource(id = R.drawable.arrow_up),
                    isDay = isDay
                )
            }
        }
    }
}