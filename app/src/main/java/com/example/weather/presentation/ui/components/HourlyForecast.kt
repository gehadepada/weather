package com.example.weather.presentation.ui.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.ui.theme.black60
import com.example.weather.ui.theme.black87
import com.example.weather.ui.theme.urbanistFontFamily
import com.example.weather.ui.theme.white70

@Composable
fun HourlyForecast(
    modifier: Modifier = Modifier,
    icon: Int = R.drawable.forecast_icon,
    text1: Int,
    text2: String,
    isDay :Int

    ) {
    Box(
        modifier = Modifier
            .width(88.dp)
            .height(140.dp)

    )
    {
        Box(
            modifier = Modifier
                .width(88.dp)
                .height(120.dp)
                .background(  color = if (isDay!=1) Color(0xB3060414) else white70, shape = RoundedCornerShape(20.dp))
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top

            ) {
                Image(
                    painter = painterResource(id= icon),
                    contentDescription = null,
                    modifier = Modifier
                        .height(58.dp)
                        .offset(y = (-27).dp)
                )
                Text(
                    text = "$text1°C",
                    fontSize = 16.sp,
                    fontFamily = urbanistFontFamily,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.25.sp,
                   color = if ((isDay!=1)) Color(0xDEFFFFFF) else black87
                )
                Text(
                    text = text2,
                    fontSize = 16.sp,
                    fontFamily = urbanistFontFamily,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.25.sp,
                    color = if (isDay!=1) Color(0x99FFFFFF) else black60
                )
            }
        }
    }

}
