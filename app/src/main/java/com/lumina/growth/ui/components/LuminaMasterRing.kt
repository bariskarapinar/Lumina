package com.lumina.growth.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LuminaMasterRing(
    progressList: List<Pair<Float, Color>>,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "ring_glow")
    val glowPulse by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        Canvas(modifier = Modifier.size(220.dp).scale(glowPulse)) {
            val strokeWidth = 14.dp.toPx()
            val spacing = 22.dp.toPx()

            progressList.forEachIndexed { index, pair ->
                val radius = (size.minDimension / 2.2f) - (index * spacing)
                
                // Track
                drawCircle(
                    color = pair.second.copy(alpha = 0.1f),
                    radius = radius,
                    style = Stroke(width = strokeWidth)
                )

                // Progress
                drawArc(
                    brush = Brush.sweepGradient(
                        colors = listOf(pair.second.copy(alpha = 0.5f), pair.second, pair.second.copy(alpha = 0.5f))
                    ),
                    startAngle = -90f,
                    sweepAngle = 360f * pair.first,
                    useCenter = false,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
                    size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2),
                    topLeft = Offset(center.x - radius, center.y - radius)
                )
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "OVERALL",
                style = MaterialTheme.typography.labelSmall,
                color = Color.White.copy(alpha = 0.6f),
                letterSpacing = 2.sp
            )
            Text(
                text = "${(progressList.map { it.first }.average() * 100).toInt()}%",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
            )
        }
    }
}
