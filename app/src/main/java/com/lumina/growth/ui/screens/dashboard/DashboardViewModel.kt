package com.lumina.growth.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import com.lumina.growth.data.MockData
import com.lumina.growth.domain.model.Affirmation
import com.lumina.growth.domain.model.Habit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DashboardViewModel : ViewModel() {
    private val _habits = MutableStateFlow(MockData.habits)
    val habits: StateFlow<List<Habit>> = _habits.asStateFlow()

    private val _affirmation = MutableStateFlow(MockData.affirmations.random())
    val affirmation: StateFlow<Affirmation> = _affirmation.asStateFlow()
    
    fun refreshAffirmation() {
        _affirmation.value = MockData.affirmations.random()
    }
}
