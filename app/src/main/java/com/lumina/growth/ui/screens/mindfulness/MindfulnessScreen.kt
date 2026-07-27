package com.lumina.growth.ui.screens.mindfulness

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lumina.growth.ui.components.LuminaAnimatedBackground
import com.lumina.growth.ui.theme.GradientOcean
import com.lumina.growth.ui.theme.LuminaTextSecondary

@Composable
fun MindfulnessScreen(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "breathing")
    
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.6f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )

    Box(modifier = modifier.fillMaxSize()) {
        LuminaAnimatedBackground()
        
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = if (scale > 1.3f) "Breathe Out..." else "Breathe In...",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    letterSpacing = 2.sp
                )
            )
            Text(
                text = "Release all your tension",
                style = MaterialTheme.typography.bodyLarge,
                color = LuminaTextSecondary
            )
            
            Spacer(modifier = Modifier.height(120.dp))
            
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(240.dp)
            ) {
                // Outer Ripple 1
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .scale(scale * 1.2f)
                        .background(
                            Brush.radialGradient(
                                listOf(Color(0xFF4facfe).copy(alpha = 0.1f), Color.Transparent)
                            ),
                            shape = CircleShape
                        )
                )

                // Outer Ripple 2
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .scale(scale)
                        .background(
                            Brush.radialGradient(
                                colors = listOf(Color(0xFF00f2fe).copy(alpha = alpha * 0.4f), Color.Transparent)
                            ),
                            shape = CircleShape
                        )
                )
                
                // Core circle
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .scale(0.8f + (scale * 0.2f))
                        .background(Color.White.copy(alpha = 0.15f), shape = CircleShape)
                        .padding(2.dp)
                        .background(
                            Brush.linearGradient(GradientOcean),
                            shape = CircleShape
                        )
                )
            }

            Spacer(modifier = Modifier.height(120.dp))
        }
    }
}
