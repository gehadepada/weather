package com.example.weather.presentation.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.ui.theme.black60
import com.example.weather.ui.theme.urbanistFontFamily

@Composable
fun MaxMinTemberture(
    temperature: Int,
    icon: Painter,
    isDay :Int
) {


    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = stringResource(id = R.string.arrow_down),
            modifier = Modifier
                .width(12.dp)
                .height(12.dp),
            tint = if ((isDay!=1)) Color(0xDEFFFFFF)
            else black60
        )

        Text(
            text = "$temperature°C",
            fontSize = 16.sp,
            fontFamily = urbanistFontFamily,
            fontWeight = FontWeight.Medium,
            color = if (isDay!=1) Color(0xDEFFFFFF)
            else black60,
            letterSpacing = 0.25.sp,

        )
    }
}