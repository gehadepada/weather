package com.example.weather.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.weather.ui.theme.black8
import com.example.weather.ui.theme.black87
import com.example.weather.ui.theme.blue100
import com.example.weather.ui.theme.urbanistFontFamily
import com.example.weather.ui.theme.white70

@Composable
fun WeatherBox(
    modifier: Modifier = Modifier,
    icon: Painter,
    textInfo1: String,
    textInfo2: String,
    isDay : Int,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .width(108.dp)
            .height(115.dp)
            .border(1.dp ,color= black8 , shape =RoundedCornerShape(24.dp) )
            .background(
              color =  if(isDay ==1) white70 else Color(0xFF060414),
                shape =RoundedCornerShape(24.dp)
            )
            .padding(horizontal = 12.dp, vertical = 14.dp)

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp),
                tint = blue100
            )
            Spacer(modifier =Modifier.height(6.dp))

            Text(
                text = textInfo1,
                fontSize = 20.sp,
                fontFamily = urbanistFontFamily,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.25.sp,
                color =  if(isDay ==1) black87 else Color(0xDEFFFFFF),
            )

            Text(
                text = textInfo2,
                fontSize = 14.sp,
                fontFamily = urbanistFontFamily,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.25.sp,
                color =  if(isDay ==1) black60 else Color(0x99FFFFFF),
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewWeatherBox() {
    WeatherBox(
        icon = painterResource(R.drawable.location_icon),
        textInfo1 = "13 KM/h",
        textInfo2 = "90",
        isDay =1
    )
}
