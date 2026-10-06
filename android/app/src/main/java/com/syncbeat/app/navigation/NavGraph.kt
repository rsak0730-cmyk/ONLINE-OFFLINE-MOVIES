package com.syncbeat.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    data object Home : Screen("home", "Home", Icons.Filled.Home, Icons.Outlined.Home)
    data object Movies : Screen("movies", "Movies", Icons.Filled.Movie, Icons.Outlined.Movie)
    data object Library : Screen("library", "Offline", Icons.Filled.Folder, Icons.Outlined.Folder)
    data object Rooms : Screen("rooms", "Rooms", Icons.Filled.Groups, Icons.Outlined.Groups)
    data object Profile : Screen("profile", "Profile", Icons.Filled.Person, Icons.Outlined.Person)
}

val bottomNavItems = listOf(
    Screen.Home,
    Screen.Movies,
    Screen.Library,
    Screen.Rooms,
    Screen.Profile
)
