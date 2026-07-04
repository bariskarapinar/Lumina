package com.lumina.growth.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.ui.graphics.Color
import com.lumina.growth.domain.model.Affirmation
import com.lumina.growth.domain.model.Habit
import com.lumina.growth.domain.model.Mood
import com.lumina.growth.domain.model.MoodEntry

object MockData {
    val habits = listOf(
        Habit(1, "Hydration", "Drink 2L water", Icons.Default.WaterDrop, Color(0xFF64B5F6), 0.7f),
        Habit(2, "Mindfulness", "10 min meditation", Icons.Default.SelfImprovement, Color(0xFF9575CD), 0.4f),
        Habit(3, "Knowledge", "Read 20 pages", Icons.AutoMirrored.Filled.MenuBook, Color(0xFFFFB74D), 0.9f, true),
        Habit(4, "Gratitude", "Write daily notes", Icons.Default.AutoAwesome, Color(0xFF81C784), 0.2f)
    )

    val moodHistory = listOf(
        MoodEntry("Mon", Mood.GREAT),
        MoodEntry("Tue", Mood.GOOD),
        MoodEntry("Wed", Mood.NEUTRAL),
        MoodEntry("Thu", Mood.GOOD),
        MoodEntry("Fri", Mood.GREAT)
    )

    val affirmations = listOf(
        Affirmation("Every day is a new beginning.", "Lumina"),
        Affirmation("Consistency is the mother of mastery.", "Lumina"),
        Affirmation("Your potential is limitless.", "Lumina"),
        Affirmation("Small steps lead to big results.", "Lumina")
    )
}
