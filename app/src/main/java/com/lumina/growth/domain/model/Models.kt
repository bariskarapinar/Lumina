package com.lumina.growth.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class Habit(
    val id: Int,
    val name: String,
    val description: String,
    val icon: ImageVector,
    val color: Color,
    val progress: Float, // 0.0 to 1.0
    val isCompleted: Boolean = false
)

enum class Mood(val label: String, val emoji: String, val color: Color) {
    GREAT("Great", "🤩", Color(0xFFFFD700)),
    GOOD("Good", "😊", Color(0xFF90EE90)),
    NEUTRAL("Okay", "😐", Color(0xFFA9A9A9)),
    BAD("Sad", "😔", Color(0xFFADD8E6)),
    AWFUL("Awful", "😫", Color(0xFFF08080))
}

data class MoodEntry(
    val date: String,
    val mood: Mood,
    val note: String = ""
)

data class Affirmation(
    val text: String,
    val author: String
)
