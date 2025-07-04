package com.example.weather.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.ui.theme.urbanistFontFamily

@Composable
fun TopTitle(
    cityName: String = "NOT fOUND",
    isDay:Int
) {
    Row(
        modifier = Modifier
    )
    {
        Icon(
            painter = painterResource(R.drawable.location_icon),
            contentDescription = stringResource(R.string.location_icon),
            modifier = Modifier
                .width(24.dp)
                .height(24.dp),
                   tint= if ((isDay!=1))Color(0xFFFFFFFF)
                    else Color(0xFF323232)

        )
        Text(
            text = cityName,
            fontSize = 16.sp,
            fontFamily = urbanistFontFamily,
            fontWeight = FontWeight.Medium,
                color = if (isDay!=1) Color(0xFFFFFFFF)
                else Color(0xFF323232)

        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopTiltlePreview() {
    TopTitle("xx" , 1)
}

