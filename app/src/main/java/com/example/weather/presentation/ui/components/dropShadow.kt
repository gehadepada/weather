package com.example.weather.presentation.ui.components

import android.graphics.BlurMaskFilter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.dropShadow(
    color: Color = Color.Black.copy(alpha = 0.25f),
    shape: androidx.compose.ui.graphics.Shape = RectangleShape,
    blurRadius: Dp = 8.dp,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 4.dp,
): Modifier = this.drawBehind {
    val shadowColor = color.toArgb()
    val frameworkPaint = android.graphics.Paint().apply {
        isAntiAlias = true
        this.color = shadowColor
        maskFilter = BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL)
    }

    drawIntoCanvas { canvas ->
        val outline = shape.createOutline(size, layoutDirection, this)
        val path = Path().apply {
            when (outline) {
                is Outline.Rectangle -> addRect(outline.rect)
                is Outline.Rounded -> addRoundRect(outline.roundRect)
                is Outline.Generic -> addPath(outline.path)
            }
        }

        canvas.save()
        canvas.nativeCanvas.translate(offsetX.toPx(), offsetY.toPx())
        canvas.nativeCanvas.drawPath(path.asAndroidPath(), frameworkPaint)
        canvas.restore()
    }
}
