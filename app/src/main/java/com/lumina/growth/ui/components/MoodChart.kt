package com.lumina.growth.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.lumina.growth.domain.model.Mood
import com.lumina.growth.domain.model.MoodEntry

@Composable
fun LuminaMoodChart(
    history: List<MoodEntry>,
    modifier: Modifier = Modifier
) {
    val animatedProgress = remember { Animatable(0f) }
    LaunchedEffect(history) {
        animatedProgress.animateTo(1f, animationSpec = tween(1500, easing = FastOutSlowInEasing))
    }

    Canvas(modifier = modifier.height(150.dp).fillMaxWidth()) {
        val path = Path()
        val moodLevels = history.map { 
            when(it.mood) {
                Mood.GREAT -> 0f
                Mood.GOOD -> 0.25f
                Mood.NEUTRAL -> 0.5f
                Mood.BAD -> 0.75f
                Mood.AWFUL -> 1f
            }
        }.reversed()

        if (moodLevels.isEmpty()) return@Canvas

        val stepX = size.width / (moodLevels.size - 1).coerceAtLeast(1)
        
        moodLevels.forEachIndexed { index, level ->
            val x = index * stepX
            val y = level * size.height
            if (index == 0) path.moveTo(x, y) else path.lineTo(x, y)
        }

        // Glow effect path
        drawPath(
            path = path,
            brush = Brush.linearGradient(listOf(Color(0xFF6C63FF), Color(0xFFFF6584))),
            style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round),
            alpha = animatedProgress.value
        )
        
        // Area below
        val fillPath = android.graphics.Path(path.asAndroidPath()).asComposePath()
        fillPath.lineTo(size.width, size.height)
        fillPath.lineTo(0f, size.height)
        fillPath.close()
        
        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = listOf(Color(0xFF6C63FF).copy(alpha = 0.2f * animatedProgress.value), Color.Transparent)
            )
        )
    }
}
