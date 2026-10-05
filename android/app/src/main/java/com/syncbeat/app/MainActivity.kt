package com.syncbeat.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.syncbeat.app.navigation.Screen
import com.syncbeat.app.navigation.bottomNavItems
import com.syncbeat.app.ui.screens.HomeScreen
import com.syncbeat.app.ui.screens.LibraryScreen
import com.syncbeat.app.ui.screens.MoviesScreen
import com.syncbeat.app.ui.screens.ProfileScreen
import com.syncbeat.app.ui.screens.RoomsScreen
import com.syncbeat.app.ui.theme.SyncBeatBg
import com.syncbeat.app.ui.theme.SyncBeatPrimary
import com.syncbeat.app.ui.theme.SyncBeatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SyncBeatTheme {
                SyncBeatApp()
            }
        }
    }
}

@Composable
fun SyncBeatApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = SyncBeatBg,
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF0E0E14),
                contentColor = Color.White
            ) {
                bottomNavItems.forEach { screen ->
                    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = if (selected) screen.selectedIcon else screen.unselectedIcon,
                                contentDescription = screen.title
                            )
                        },
                        label = { Text(screen.title) },
                        selected = selected,
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
                            selectedIconColor = SyncBeatPrimary,
                            selectedTextColor = SyncBeatPrimary,
                            unselectedIconColor = Color(0xFF9E9E9E),
                            unselectedTextColor = Color(0xFF9E9E9E),
                            indicatorColor = Color(0xFF1A1A2E)
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Movies.route) { MoviesScreen() }
            composable(Screen.Library.route) { LibraryScreen() }
            composable(Screen.Rooms.route) { RoomsScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
        }
    }
}
