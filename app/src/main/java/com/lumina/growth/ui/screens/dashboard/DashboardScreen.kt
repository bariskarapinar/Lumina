package com.lumina.growth.ui.screens.dashboard

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lumina.growth.ui.components.LuminaAnimatedBackground
import com.lumina.growth.ui.components.LuminaCard
import com.lumina.growth.ui.components.LuminaFloatingParticles
import com.lumina.growth.ui.components.LuminaMasterRing
import com.lumina.growth.ui.theme.GradientLumina
import com.lumina.growth.ui.theme.LuminaTextSecondary

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = viewModel()
) {
    val habits by viewModel.habits.collectAsStateWithLifecycle()
    val affirmation by viewModel.affirmation.collectAsStateWithLifecycle()

    Box(modifier = modifier.fillMaxSize()) {
        LuminaAnimatedBackground()
        LuminaFloatingParticles()
        
        Scaffold(
            containerColor = Color.Transparent
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Header()
                }

                item {
                    Box(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        LuminaMasterRing(
                            progressList = habits.take(3).map { it.progress to it.color }
                        )
                    }
                }

                item {
                    AffirmationCard(
                        text = affirmation.text,
                        onRefresh = { viewModel.refreshAffirmation() }
                    )
                }

                item {
                    Text(
                        text = "Daily Summary",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            letterSpacing = 1.sp
                        )
                    )
                }

                items(habits.take(4)) { habit ->
                    HabitSummaryItem(habit.name, habit.progress, habit.color)
                }
                
                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Welcome to",
                style = MaterialTheme.typography.bodyMedium,
                color = LuminaTextSecondary
            )
            Text(
                text = "Lumina Growth",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
            )
        }
        IconButton(onClick = { }) {
            Icon(
                Icons.Default.Notifications,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Composable
fun AffirmationCard(text: String, onRefresh: () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.02f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    LuminaCard(
        modifier = Modifier
            .fillMaxWidth()
            .scale(pulseScale),
        gradient = GradientLumina
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "DAILY AFFIRMATION",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    ),
                    color = LuminaTextSecondary
                )
                IconButton(onClick = onRefresh, modifier = Modifier.size(24.dp)) {
                    Icon(Icons.Default.Refresh, contentDescription = null, tint = LuminaTextSecondary, modifier = Modifier.size(16.dp))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "\"$text\"",
                style = MaterialTheme.typography.headlineSmall.copy(
                    lineHeight = 36.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White
            )
        }
    }
}

@Composable
fun HabitSummaryItem(name: String, progress: Float, color: Color) {
    LuminaCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = name, 
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
                Text(
                    text = "${(progress * 100).toInt()}% completed", 
                    style = MaterialTheme.typography.bodySmall,
                    color = LuminaTextSecondary
                )
            }
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.size(40.dp),
                    color = color,
                    trackColor = color.copy(alpha = 0.1f),
                    strokeCap = StrokeCap.Round
                )
            }
        }
    }
}
