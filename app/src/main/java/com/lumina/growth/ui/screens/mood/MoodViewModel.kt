package com.lumina.growth.ui.screens.mood

import androidx.lifecycle.ViewModel
import com.lumina.growth.data.MockData
import com.lumina.growth.domain.model.Mood
import com.lumina.growth.domain.model.MoodEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MoodViewModel : ViewModel() {
    private val _moodHistory = MutableStateFlow(MockData.moodHistory)
    val moodHistory: StateFlow<List<MoodEntry>> = _moodHistory.asStateFlow()

    fun addMood(mood: Mood) {
        val newEntry = MoodEntry("Today", mood)
        _moodHistory.value = listOf(newEntry) + _moodHistory.value
    }
}
