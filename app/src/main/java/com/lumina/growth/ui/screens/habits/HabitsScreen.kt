package com.lumina.growth.ui.screens.habits

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lumina.growth.ui.components.CircularLuminaProgress
import com.lumina.growth.ui.components.LuminaAnimatedBackground
import com.lumina.growth.ui.components.LuminaCard
import com.lumina.growth.ui.components.LuminaConfetti
import com.lumina.growth.ui.theme.LuminaTextSecondary
import kotlinx.coroutines.delay

@Composable
fun HabitsScreen(
    modifier: Modifier = Modifier,
    viewModel: HabitsViewModel = viewModel()
) {
    val habits by viewModel.habits.collectAsStateWithLifecycle()
    var showConfetti by remember { mutableStateOf(false) }

    LaunchedEffect(showConfetti) {
        if (showConfetti) {
            delay(1500)
            showConfetti = false
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        LuminaAnimatedBackground()
        
        Scaffold(
            containerColor = Color.Transparent,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White,
                    shape = CircleShape
                ) {
                    Icon(Icons.Default.Add, contentDescription = null)
                }
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Habit Tracking",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                    )
                    Text(
                        text = "Every small step counts towards greatness.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = LuminaTextSecondary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                itemsIndexed(habits) { index, habit ->
                    val animatedVisible = remember { mutableStateOf(false) }
                    LaunchedEffect(Unit) {
                        delay(index * 100L)
                        animatedVisible.value = true
                    }
                    
                    AnimatedVisibility(
                        visible = animatedVisible.value,
                        enter = fadeIn() + slideInHorizontally()
                    ) {
                        HabitItem(
                            name = habit.name,
                            progress = habit.progress,
                            color = habit.color,
                            isCompleted = habit.isCompleted,
                            onToggle = { 
                                viewModel.toggleHabit(habit.id)
                                if (!habit.isCompleted) showConfetti = true
                            }
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }

        LuminaConfetti(trigger = showConfetti)
    }
}

@Composable
fun HabitItem(
    name: String, 
    progress: Float, 
    color: Color, 
    isCompleted: Boolean,
    onToggle: () -> Unit
) {
    LuminaCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                CircularLuminaProgress(
                    progress = progress,
                    size = 52.dp,
                    strokeWidth = 5.dp,
                    gradient = listOf(color, color.copy(alpha = 0.3f))
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                    Text(
                        text = if (isCompleted) "Excellent work!" else "Keep up the momentum",
                        style = MaterialTheme.typography.bodySmall,
                        color = LuminaTextSecondary
                    )
                }
            }
            
            IconButton(
                onClick = onToggle,
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(if (isCompleted) color else color.copy(alpha = 0.1f))
            ) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = null,
                    tint = if (isCompleted) Color.White else color
                )
            }
        }
    }
}
