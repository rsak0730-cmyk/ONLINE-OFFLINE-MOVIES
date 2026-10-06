package com.syncbeat.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalAppTheme = staticCompositionLocalOf { AppThemes.NeumorphismLight }

object SyncBeatThemeColors {
    val current: AppThemeColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTheme.current
}

@Composable
fun SyncBeatTheme(
    theme: AppThemeColors = AppThemes.NeumorphismLight,
    content: @Composable () -> Unit
) {
    val colorScheme = if (theme.isDark) {
        darkColorScheme(
            primary = theme.primary,
            secondary = theme.secondary,
            background = theme.background,
            surface = theme.surface,
            onPrimary = theme.onPrimary,
            onSecondary = theme.onPrimary,
            onBackground = theme.onBackground,
            onSurface = theme.onSurface,
            surfaceVariant = theme.surfaceElevated,
            onSurfaceVariant = theme.muted,
            outline = theme.border,
            error = theme.error
        )
    } else {
        lightColorScheme(
            primary = theme.primary,
            secondary = theme.secondary,
            background = theme.background,
            surface = theme.surface,
            onPrimary = theme.onPrimary,
            onSecondary = theme.onPrimary,
            onBackground = theme.onBackground,
            onSurface = theme.onSurface,
            surfaceVariant = theme.surfaceElevated,
            onSurfaceVariant = theme.muted,
            outline = theme.border,
            error = theme.error
        )
    }

    CompositionLocalProvider(LocalAppTheme provides theme) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}
