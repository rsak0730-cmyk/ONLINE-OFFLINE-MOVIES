package com.syncbeat.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val SyncBeatBg = Color(0xFF07070A)
val SyncBeatSurface = Color(0xFF121218)
val SyncBeatPrimary = Color(0xFF7C4DFF)
val SyncBeatSecondary = Color(0xFF00E5FF)
val SyncBeatOnBg = Color(0xFFFFFFFF)
val SyncBeatMuted = Color(0xFF9E9E9E)
val SyncBeatCard = Color(0xFF1A1A24)

private val DarkColorScheme = darkColorScheme(
    primary = SyncBeatPrimary,
    secondary = SyncBeatSecondary,
    background = SyncBeatBg,
    surface = SyncBeatSurface,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = SyncBeatOnBg,
    onSurface = SyncBeatOnBg,
    surfaceVariant = SyncBeatCard,
    onSurfaceVariant = SyncBeatMuted
)

@Composable
fun SyncBeatTheme(
    darkTheme: Boolean = true, // always dark for this app
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
