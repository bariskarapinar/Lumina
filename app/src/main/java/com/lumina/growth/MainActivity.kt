package com.lumina.growth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.EmojiEmotions
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.lumina.growth.ui.components.LuminaAnimatedBackground
import com.lumina.growth.ui.components.LuminaFloatingParticles
import com.lumina.growth.ui.screens.dashboard.DashboardScreen
import com.lumina.growth.ui.screens.habits.HabitsScreen
import com.lumina.growth.ui.screens.mindfulness.MindfulnessScreen
import com.lumina.growth.ui.screens.mood.MoodTrackerScreen
import com.lumina.growth.ui.theme.LuminaTheme

@Composable
fun LuminaThemeWrapper(content: @Composable () -> Unit) {
    LuminaTheme(darkTheme = true) {
        content()
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LuminaThemeWrapper {
                MainScreen()
            }
        }
    }
}

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Dashboard : Screen("dashboard", "Dashboard", Icons.Default.Dashboard)
    object Habits : Screen("habits", "Habits", Icons.AutoMirrored.Filled.List)
    object Mood : Screen("mood", "Mood", Icons.Default.EmojiEmotions)
    object Mindfulness : Screen("mindfulness", "Mind", Icons.Default.SelfImprovement)
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = Color.Transparent,
                contentColor = Color.White
            ) {
                val items = listOf(
                    Screen.Dashboard,
                    Screen.Habits,
                    Screen.Mood,
                    Screen.Mindfulness
                )
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = null, modifier = Modifier.size(26.dp)) },
                        label = { Text(screen.label, fontWeight = FontWeight.Bold, fontSize = 10.sp) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            selectedTextColor = Color.White,
                            unselectedIconColor = Color.White.copy(alpha = 0.4f),
                            unselectedTextColor = Color.White.copy(alpha = 0.4f),
                            indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            LuminaAnimatedBackground()
            LuminaFloatingParticles()
            NavHost(
                navController,
                startDestination = Screen.Dashboard.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Dashboard.route) { DashboardScreen() }
                composable(Screen.Habits.route) { HabitsScreen() }
                composable(Screen.Mood.route) { MoodTrackerScreen() }
                composable(Screen.Mindfulness.route) { MindfulnessScreen() }
            }
        }
    }
}
