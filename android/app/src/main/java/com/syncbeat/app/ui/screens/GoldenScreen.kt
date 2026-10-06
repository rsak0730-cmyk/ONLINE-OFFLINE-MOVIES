package com.syncbeat.app.ui.screens

import androidx.compose.runtime.Composable
import com.syncbeat.app.ui.components.WebRoomScreen

private const val BASE = "https://rsak0730-cmyk.github.io/ONLINE-OFFLINE-MOVIES"

/** Golden Room tab — golden.html only (no hub tabs) */
@Composable
fun GoldenScreen() {
    WebRoomScreen(pageUrl = "$BASE/golden.html")
}
