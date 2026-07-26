package com.lumina.growth.ui.screens.habits

import androidx.lifecycle.ViewModel
import com.lumina.growth.data.MockData
import com.lumina.growth.domain.model.Habit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HabitsViewModel : ViewModel() {
    private val _habits = MutableStateFlow(MockData.habits)
    val habits: StateFlow<List<Habit>> = _habits.asStateFlow()

    fun toggleHabit(habitId: Int) {
        _habits.value = _habits.value.map {
            if (it.id == habitId) it.copy(isCompleted = !it.isCompleted, progress = if (!it.isCompleted) 1.0f else it.progress)
            else it
        }
    }
}
