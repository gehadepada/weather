package com.example.weather.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.ui.theme.black24
import com.example.weather.ui.theme.black60
import com.example.weather.ui.theme.black87
import com.example.weather.ui.theme.urbanistFontFamily
import com.example.weather.ui.theme.white70

@Composable
fun sevenDaysForecast(
    day :String ,
    icon : Int ,
    max : Int ,
    min : Int,
    isDay:Int

)
{
    Row (
       // verticalAlignment = Alignment.,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .width(336.dp)
            .height(61.dp)
            .padding(vertical =8.dp , horizontal =12.dp)

    ){
        Text(
            text = day,
            fontSize = 16.sp,
            fontFamily = urbanistFontFamily,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.25.sp,
            color = if ((isDay!=1)) Color(0x99FFFFFF) else black60
        )
        Image(
            painter = painterResource(id=icon),
            contentDescription = null,
            modifier = Modifier.size(32.dp),
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            MaxMinTemberture(
                temperature = max,
                icon = painterResource(id = R.drawable.arrow_up),
                isDay=isDay
            )

            Text(
                text = "|",
                fontSize = 14.sp,
                fontFamily = urbanistFontFamily,
                fontWeight = FontWeight.Medium,
                color = black24,
            )
            MaxMinTemberture(
                temperature = min,
                icon = painterResource(id = R.drawable.arrow_down_04),
                isDay=isDay
            )
        }

    }


}
