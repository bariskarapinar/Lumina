package com.lumina.growth.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.lumina.growth.ui.theme.LuminaBackground

@Composable
fun LuminaAnimatedBackground() {
    val infiniteTransition = rememberInfiniteTransition(label = "background")
    
    val xOffset1 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "x1"
    )
    
    val yOffset1 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1500f,
        animationSpec = infiniteRepeatable(
            animation = tween(25000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "y1"
    )

    val xOffset2 by infiniteTransition.animateFloat(
        initialValue = 1000f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(22000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "x2"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRect(color = LuminaBackground)
            
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFF6C63FF).copy(alpha = 0.15f), Color.Transparent),
                    center = Offset(xOffset1, yOffset1),
                    radius = 800f
                ),
                radius = 800f,
                center = Offset(xOffset1, yOffset1)
            )
            
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFFF6584).copy(alpha = 0.12f), Color.Transparent),
                    center = Offset(xOffset2, 500f),
                    radius = 900f
                ),
                radius = 900f,
                center = Offset(xOffset2, 500f)
            )
            
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFF43E97B).copy(alpha = 0.1f), Color.Transparent),
                    center = Offset(500f, 2000f - yOffset1),
                    radius = 700f
                ),
                radius = 700f,
                center = Offset(500f, 2000f - yOffset1)
            )
        }
    }
}
