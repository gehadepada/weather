package com.example.weather.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.R
@Composable

fun WeatherImage(
    modifier: Modifier = Modifier,
    image: Int = R.drawable.ic_launcher_foreground,
    headerIconScale :Float,
    headerIconOffsetX:Float,
    headerIconOffsetY:Float,
) {
        Image(
            painter = painterResource(id = image),
            contentDescription = stringResource(id = R.string.weather_image),
            modifier = modifier
                .width(220.21.dp)
                .height(200.dp)
                .graphicsLayer {
                    scaleX = headerIconScale
                    scaleY = headerIconScale
                    translationX = with(density) { headerIconOffsetX.dp.toPx() }
                    translationY = with(density) { headerIconOffsetY.dp.toPx() }
                }
        )
}



