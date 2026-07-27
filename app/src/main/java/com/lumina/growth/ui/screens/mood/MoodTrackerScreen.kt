package com.lumina.growth.ui.screens.mood

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lumina.growth.domain.model.Mood
import com.lumina.growth.ui.components.LuminaAnimatedBackground
import com.lumina.growth.ui.components.LuminaCard
import com.lumina.growth.ui.components.LuminaMoodChart
import com.lumina.growth.ui.theme.LuminaTextSecondary

@Composable
fun MoodTrackerScreen(
    modifier: Modifier = Modifier,
    viewModel: MoodViewModel = viewModel()
) {
    val moodHistory by viewModel.moodHistory.collectAsStateWithLifecycle()
    var selectedMood by remember { mutableStateOf<Mood?>(null) }

    Box(modifier = modifier.fillMaxSize()) {
        LuminaAnimatedBackground()
        
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
                    Text(
                        text = "Mood Analytics",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                    )
                    Text(
                        text = "Visualizing your emotional journey.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = LuminaTextSecondary
                    )
                }

                item {
                    LuminaCard(modifier = Modifier.fillMaxWidth()) {
                        LuminaMoodChart(history = moodHistory)
                    }
                }

                item {
                    LuminaCard(modifier = Modifier.fillMaxWidth()) {
                        Column {
                            Text(
                                text = "HOW ARE YOU TODAY?",
                                style = MaterialTheme.typography.labelLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 2.sp
                                ),
                                color = LuminaTextSecondary
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Mood.entries.forEach { mood ->
                                    MoodSelectorItem(
                                        mood = mood,
                                        isSelected = selectedMood == mood,
                                        onSelect = { 
                                            selectedMood = mood
                                            viewModel.addMood(mood)
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

                item {
                    Text(
                        text = "Recent Logs",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                    )
                }

                items(moodHistory) { entry ->
                    MoodHistoryItem(entry.date, entry.mood)
                }

                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }
}

@Composable
fun MoodSelectorItem(mood: Mood, isSelected: Boolean, onSelect: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onSelect() }
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(if (isSelected) mood.color else mood.color.copy(alpha = 0.1f))
                .then(
                    if (isSelected) {
                        Modifier.border(2.dp, Color.White.copy(alpha = 0.5f), CircleShape)
                    } else Modifier
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(text = mood.emoji, fontSize = 28.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = mood.label, 
            style = MaterialTheme.typography.labelSmall, 
            color = if (isSelected) Color.White else LuminaTextSecondary,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun MoodHistoryItem(date: String, mood: Mood) {
    LuminaCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(mood.color.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = mood.emoji, fontSize = 22.sp)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = date, 
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                    Text(
                        text = mood.label, 
                        style = MaterialTheme.typography.bodySmall, 
                        color = LuminaTextSecondary
                    )
                }
            }
            
            Text(
                text = "Details",
                style = MaterialTheme.typography.labelMedium,
                color = mood.color,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
