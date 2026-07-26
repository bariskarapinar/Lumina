package com.lumina.growth.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

@Composable
fun LuminaConfetti(trigger: Boolean) {
    if (!trigger) return

    val particles = remember {
        List(40) {
            ConfettiState(
                color = listOf(Color(0xFF6C63FF), Color(0xFFFF6584), Color(0xFF43E97B), Color(0xFFFFD93D)).random(),
                x = 0.5f,
                y = 0.5f,
                vx = Random.nextFloat() * 20f - 10f,
                vy = Random.nextFloat() * -30f - 5f,
                size = Random.nextFloat() * 10f + 5f
            )
        }
    }

    val animatable = remember { Animatable(0f) }
    
    LaunchedEffect(trigger) {
        animatable.animateTo(1f, animationSpec = tween(1500, easing = LinearOutSlowInEasing))
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        particles.forEach { p ->
            val time = animatable.value
            val currentX = p.x * size.width + (p.vx * time * 50f)
            val currentY = p.y * size.height + (p.vy * time * 50f) + (0.5f * 9.8f * time * time * 1000f)
            
            drawCircle(
                color = p.color.copy(alpha = 1f - time),
                radius = p.size,
                center = Offset(currentX, currentY)
            )
        }
    }
}

private data class ConfettiState(
    val color: Color,
    val x: Float,
    val y: Float,
    val vx: Float,
    val vy: Float,
    val size: Float
)
