package com.lumina.growth.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LuminaCard(
    modifier: Modifier = Modifier,
    gradient: List<Color>? = null,
    borderAlpha: Float = 0.2f,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White.copy(alpha = 0.05f))
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    gradient ?: listOf(Color.White.copy(alpha = borderAlpha), Color.White.copy(alpha = 0.05f))
                ),
                shape = RoundedCornerShape(24.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .background(
                    if (gradient != null) {
                        Brush.linearGradient(
                            colors = gradient.map { it.copy(alpha = 0.15f) }
                        )
                    } else {
                        Brush.linearGradient(listOf(Color.Transparent, Color.Transparent))
                    }
                )
                .padding(20.dp)
        ) {
            content()
        }
    }
}
