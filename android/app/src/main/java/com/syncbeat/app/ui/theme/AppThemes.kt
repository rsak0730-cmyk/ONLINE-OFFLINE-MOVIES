package com.syncbeat.app.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * Full theme palette for SyncBeat.
 * Neumorphism Light is the MAIN / default theme.
 */
data class AppThemeColors(
    val id: String,
    val name: String,
    val isNeumorphic: Boolean,
    val isDark: Boolean,
    val background: Color,
    val surface: Color,
    val surfaceElevated: Color,
    val primary: Color,
    val secondary: Color,
    val onBackground: Color,
    val onSurface: Color,
    val onPrimary: Color,
    val muted: Color,
    val accent: Color,
    val lightShadow: Color,
    val darkShadow: Color,
    val border: Color,
    val success: Color,
    val error: Color
)

object AppThemes {
    val NeumorphismLight = AppThemeColors(
        id = "neu_light",
        name = "Neumorphism",
        isNeumorphic = true,
        isDark = false,
        background = Color(0xFFE0E5EC),
        surface = Color(0xFFE0E5EC),
        surfaceElevated = Color(0xFFE8EDF4),
        primary = Color(0xFF6C63FF),
        secondary = Color(0xFF00C9A7),
        onBackground = Color(0xFF2D3436),
        onSurface = Color(0xFF2D3436),
        onPrimary = Color.White,
        muted = Color(0xFF7F8C8D),
        accent = Color(0xFF6C63FF),
        lightShadow = Color(0xFFFFFFFF),
        darkShadow = Color(0xFFA3B1C6),
        border = Color(0xFFD1D9E6),
        success = Color(0xFF00C853),
        error = Color(0xFFE53935)
    )

    val NeumorphismDark = AppThemeColors(
        id = "neu_dark",
        name = "Neumorphism Dark",
        isNeumorphic = true,
        isDark = true,
        background = Color(0xFF2C2F36),
        surface = Color(0xFF2C2F36),
        surfaceElevated = Color(0xFF353940),
        primary = Color(0xFF8B83FF),
        secondary = Color(0xFF00E5C0),
        onBackground = Color(0xFFE8EAED),
        onSurface = Color(0xFFE8EAED),
        onPrimary = Color.White,
        muted = Color(0xFF9AA0A6),
        accent = Color(0xFF8B83FF),
        lightShadow = Color(0xFF3A3E48),
        darkShadow = Color(0xFF1A1C20),
        border = Color(0xFF3A3E48),
        success = Color(0xFF00E676),
        error = Color(0xFFEF5350)
    )

    val SoftClay = AppThemeColors(
        id = "soft_clay",
        name = "Soft Clay",
        isNeumorphic = true,
        isDark = false,
        background = Color(0xFFF5EDE4),
        surface = Color(0xFFF5EDE4),
        surfaceElevated = Color(0xFFFAF6F1),
        primary = Color(0xFFD4845C),
        secondary = Color(0xFF8B6F5C),
        onBackground = Color(0xFF3E2C23),
        onSurface = Color(0xFF3E2C23),
        onPrimary = Color.White,
        muted = Color(0xFF9A7B6A),
        accent = Color(0xFFD4845C),
        lightShadow = Color(0xFFFFFFFF),
        darkShadow = Color(0xFFD4C4B5),
        border = Color(0xFFE8D9CC),
        success = Color(0xFF7CB342),
        error = Color(0xFFC62828)
    )

    val OceanBreeze = AppThemeColors(
        id = "ocean",
        name = "Ocean Breeze",
        isNeumorphic = true,
        isDark = false,
        background = Color(0xFFE0F2F1),
        surface = Color(0xFFE0F2F1),
        surfaceElevated = Color(0xFFE8F6F5),
        primary = Color(0xFF00897B),
        secondary = Color(0xFF26C6DA),
        onBackground = Color(0xFF004D40),
        onSurface = Color(0xFF004D40),
        onPrimary = Color.White,
        muted = Color(0xFF5D8A84),
        accent = Color(0xFF00ACC1),
        lightShadow = Color(0xFFFFFFFF),
        darkShadow = Color(0xFFA8C9C5),
        border = Color(0xFFB2DFDB),
        success = Color(0xFF00897B),
        error = Color(0xFFE53935)
    )

    val MidnightAmoled = AppThemeColors(
        id = "amoled",
        name = "Midnight AMOLED",
        isNeumorphic = false,
        isDark = true,
        background = Color(0xFF000000),
        surface = Color(0xFF0A0A0A),
        surfaceElevated = Color(0xFF141414),
        primary = Color(0xFF7C4DFF),
        secondary = Color(0xFF00E5FF),
        onBackground = Color(0xFFFFFFFF),
        onSurface = Color(0xFFFFFFFF),
        onPrimary = Color.White,
        muted = Color(0xFF888888),
        accent = Color(0xFF7C4DFF),
        lightShadow = Color(0xFF1A1A1A),
        darkShadow = Color(0xFF000000),
        border = Color(0xFF222222),
        success = Color(0xFF00E676),
        error = Color(0xFFFF5252)
    )

    val SunsetGlow = AppThemeColors(
        id = "sunset",
        name = "Sunset Glow",
        isNeumorphic = false,
        isDark = true,
        background = Color(0xFF1A0F0A),
        surface = Color(0xFF241610),
        surfaceElevated = Color(0xFF2E1C14),
        primary = Color(0xFFFF6D00),
        secondary = Color(0xFFFFAB40),
        onBackground = Color(0xFFFFF3E0),
        onSurface = Color(0xFFFFF3E0),
        onPrimary = Color.White,
        muted = Color(0xFFBCAAA4),
        accent = Color(0xFFFF9100),
        lightShadow = Color(0xFF3E2723),
        darkShadow = Color(0xFF0D0705),
        border = Color(0xFF4E342E),
        success = Color(0xFF66BB6A),
        error = Color(0xFFEF5350)
    )

    val ForestMist = AppThemeColors(
        id = "forest",
        name = "Forest Mist",
        isNeumorphic = true,
        isDark = false,
        background = Color(0xFFE8F0E8),
        surface = Color(0xFFE8F0E8),
        surfaceElevated = Color(0xFFEEF5EE),
        primary = Color(0xFF2E7D32),
        secondary = Color(0xFF66BB6A),
        onBackground = Color(0xFF1B3A1D),
        onSurface = Color(0xFF1B3A1D),
        onPrimary = Color.White,
        muted = Color(0xFF6B8E6B),
        accent = Color(0xFF43A047),
        lightShadow = Color(0xFFFFFFFF),
        darkShadow = Color(0xFFB0C4B0),
        border = Color(0xFFC8DCC8),
        success = Color(0xFF2E7D32),
        error = Color(0xFFC62828)
    )

    val PurpleHaze = AppThemeColors(
        id = "purple",
        name = "Purple Haze",
        isNeumorphic = false,
        isDark = true,
        background = Color(0xFF12081F),
        surface = Color(0xFF1A0F2E),
        surfaceElevated = Color(0xFF24153D),
        primary = Color(0xFFB388FF),
        secondary = Color(0xFFE040FB),
        onBackground = Color(0xFFF3E5F5),
        onSurface = Color(0xFFF3E5F5),
        onPrimary = Color(0xFF1A0033),
        muted = Color(0xFFB39DDB),
        accent = Color(0xFFD500F9),
        lightShadow = Color(0xFF2A1A45),
        darkShadow = Color(0xFF080410),
        border = Color(0xFF311B4D),
        success = Color(0xFF69F0AE),
        error = Color(0xFFFF5252)
    )

    val CyberNeon = AppThemeColors(
        id = "cyber",
        name = "Cyber Neon",
        isNeumorphic = false,
        isDark = true,
        background = Color(0xFF0A0E17),
        surface = Color(0xFF0F1520),
        surfaceElevated = Color(0xFF151C2A),
        primary = Color(0xFF00F0FF),
        secondary = Color(0xFFFF00E5),
        onBackground = Color(0xFFE0F7FA),
        onSurface = Color(0xFFE0F7FA),
        onPrimary = Color(0xFF001018),
        muted = Color(0xFF607D8B),
        accent = Color(0xFF00E5FF),
        lightShadow = Color(0xFF1A2333),
        darkShadow = Color(0xFF05070C),
        border = Color(0xFF1A2A3A),
        success = Color(0xFF00FF9F),
        error = Color(0xFFFF0055)
    )

    val WarmSand = AppThemeColors(
        id = "sand",
        name = "Warm Sand",
        isNeumorphic = true,
        isDark = false,
        background = Color(0xFFF5F0E6),
        surface = Color(0xFFF5F0E6),
        surfaceElevated = Color(0xFFFAF7F0),
        primary = Color(0xFFC9A227),
        secondary = Color(0xFF8D6E63),
        onBackground = Color(0xFF3E3226),
        onSurface = Color(0xFF3E3226),
        onPrimary = Color.White,
        muted = Color(0xFF9E8E7A),
        accent = Color(0xFFD4A84B),
        lightShadow = Color(0xFFFFFFFF),
        darkShadow = Color(0xFFD4C9B5),
        border = Color(0xFFE8DFD0),
        success = Color(0xFF7CB342),
        error = Color(0xFFD32F2F)
    )

    val IceCrystal = AppThemeColors(
        id = "ice",
        name = "Ice Crystal",
        isNeumorphic = true,
        isDark = false,
        background = Color(0xFFE8F4FC),
        surface = Color(0xFFE8F4FC),
        surfaceElevated = Color(0xFFEFF8FE),
        primary = Color(0xFF0288D1),
        secondary = Color(0xFF4FC3F7),
        onBackground = Color(0xFF0D3B56),
        onSurface = Color(0xFF0D3B56),
        onPrimary = Color.White,
        muted = Color(0xFF5A8BA8),
        accent = Color(0xFF039BE5),
        lightShadow = Color(0xFFFFFFFF),
        darkShadow = Color(0xFFA8C8DC),
        border = Color(0xFFB3D9F0),
        success = Color(0xFF00ACC1),
        error = Color(0xFFE53935)
    )

    val RoseGold = AppThemeColors(
        id = "rose",
        name = "Rose Gold",
        isNeumorphic = true,
        isDark = false,
        background = Color(0xFFF8ECEC),
        surface = Color(0xFFF8ECEC),
        surfaceElevated = Color(0xFFFCF4F4),
        primary = Color(0xFFC48B8B),
        secondary = Color(0xFFD4A5A5),
        onBackground = Color(0xFF4A2C2C),
        onSurface = Color(0xFF4A2C2C),
        onPrimary = Color.White,
        muted = Color(0xFFA08080),
        accent = Color(0xFFB76E79),
        lightShadow = Color(0xFFFFFFFF),
        darkShadow = Color(0xFFD4B8B8),
        border = Color(0xFFE8D0D0),
        success = Color(0xFF81C784),
        error = Color(0xFFE57373)
    )

    val HighContrast = AppThemeColors(
        id = "contrast",
        name = "High Contrast",
        isNeumorphic = false,
        isDark = true,
        background = Color(0xFF000000),
        surface = Color(0xFF111111),
        surfaceElevated = Color(0xFF1A1A1A),
        primary = Color(0xFFFFFF00),
        secondary = Color(0xFF00FFFF),
        onBackground = Color(0xFFFFFFFF),
        onSurface = Color(0xFFFFFFFF),
        onPrimary = Color(0xFF000000),
        muted = Color(0xFFCCCCCC),
        accent = Color(0xFFFFFF00),
        lightShadow = Color(0xFF333333),
        darkShadow = Color(0xFF000000),
        border = Color(0xFFFFFFFF),
        success = Color(0xFF00FF00),
        error = Color(0xFFFF0000)
    )

    val EmeraldNight = AppThemeColors(
        id = "emerald",
        name = "Emerald Night",
        isNeumorphic = false,
        isDark = true,
        background = Color(0xFF0A1410),
        surface = Color(0xFF0F1C16),
        surfaceElevated = Color(0xFF15241C),
        primary = Color(0xFF00C853),
        secondary = Color(0xFF69F0AE),
        onBackground = Color(0xFFE8F5E9),
        onSurface = Color(0xFFE8F5E9),
        onPrimary = Color(0xFF003310),
        muted = Color(0xFF81A88A),
        accent = Color(0xFF00E676),
        lightShadow = Color(0xFF1A2E22),
        darkShadow = Color(0xFF050A08),
        border = Color(0xFF1B3A2A),
        success = Color(0xFF00E676),
        error = Color(0xFFFF5252)
    )

    val all: List<AppThemeColors> = listOf(
        NeumorphismLight,   // MAIN DEFAULT
        NeumorphismDark,
        SoftClay,
        OceanBreeze,
        IceCrystal,
        WarmSand,
        RoseGold,
        ForestMist,
        MidnightAmoled,
        SunsetGlow,
        PurpleHaze,
        CyberNeon,
        EmeraldNight,
        HighContrast
    )

    fun byId(id: String): AppThemeColors =
        all.find { it.id == id } ?: NeumorphismLight
}
