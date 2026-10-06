package com.syncbeat.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.syncbeat.app.data.ThemeManager
import com.syncbeat.app.navigation.Screen
import com.syncbeat.app.navigation.bottomNavItems
import com.syncbeat.app.ui.screens.GoldenScreen
import com.syncbeat.app.ui.screens.LibraryScreen
import com.syncbeat.app.ui.screens.MoviesScreen
import com.syncbeat.app.ui.screens.MusicScreen
import com.syncbeat.app.ui.screens.ProfileScreen
import com.syncbeat.app.ui.theme.AppThemes
import com.syncbeat.app.ui.theme.LocalAppTheme
import com.syncbeat.app.ui.theme.SyncBeatTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val themeManager = remember { ThemeManager(context) }
            val theme by themeManager.themeFlow.collectAsState(initial = AppThemes.NeumorphismLight)
            val scope = rememberCoroutineScope()

            SyncBeatTheme(theme = theme) {
                SyncBeatApp(
                    onThemeSelected = { id ->
                        scope.launch { themeManager.setTheme(id) }
                    }
                )
            }
        }
    }
}

@Composable
fun SyncBeatApp(onThemeSelected: (String) -> Unit) {
    val theme = LocalAppTheme.current
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = theme.background,
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 16.dp,
                        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
                        ambientColor = theme.darkShadow.copy(alpha = 0.3f),
                        spotColor = theme.darkShadow.copy(alpha = 0.4f)
                    )
                    .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                    .background(theme.surfaceElevated)
            ) {
                NavigationBar(
                    containerColor = Color.Transparent,
                    contentColor = theme.onSurface,
                    tonalElevation = 0.dp,
                    modifier = Modifier.height(72.dp)
                ) {
                    bottomNavItems.forEach { screen ->
                        val selected =
                            currentDestination?.hierarchy?.any { it.route == screen.route } == true
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = if (selected) screen.selectedIcon else screen.unselectedIcon,
                                    contentDescription = screen.title,
                                    modifier = Modifier.size(24.dp)
                                )
                            },
                            label = {
                                Text(text = screen.title, fontSize = 11.sp)
                            },
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
                                selectedIconColor = theme.primary,
                                selectedTextColor = theme.primary,
                                unselectedIconColor = theme.muted,
                                unselectedTextColor = theme.muted,
                                indicatorColor = theme.primary.copy(alpha = 0.12f)
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Movies.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Movies.route) { MoviesScreen() }
            composable(Screen.Golden.route) { GoldenScreen() }
            composable(Screen.Music.route) { MusicScreen() }
            composable(Screen.Library.route) { LibraryScreen() }
            composable(Screen.Profile.route) {
                ProfileScreen(onThemeSelected = onThemeSelected)
            }
        }
    }
}
