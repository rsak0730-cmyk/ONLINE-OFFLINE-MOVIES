package com.syncbeat.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    data object Movies : Screen("movies", "Movies", Icons.Filled.Movie, Icons.Outlined.Movie)
    data object Golden : Screen("golden", "Golden", Icons.Filled.Star, Icons.Outlined.Star)
    data object Music : Screen("music", "Music", Icons.Filled.MusicNote, Icons.Outlined.MusicNote)
    data object Library : Screen("library", "Offline", Icons.Filled.Folder, Icons.Outlined.Folder)
    data object Profile : Screen("profile", "Profile", Icons.Filled.Person, Icons.Outlined.Person)
}

val bottomNavItems = listOf(
    Screen.Movies,
    Screen.Golden,
    Screen.Music,
    Screen.Library,
    Screen.Profile
)
