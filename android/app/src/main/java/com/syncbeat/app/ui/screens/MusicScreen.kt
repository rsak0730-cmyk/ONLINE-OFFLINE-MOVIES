package com.syncbeat.app.ui.screens

import androidx.compose.runtime.Composable
import com.syncbeat.app.ui.components.WebRoomScreen

private const val BASE = "https://rsak0730-cmyk.github.io/ONLINE-OFFLINE-MOVIES"

/** Music Room tab — audio.html only (no hub tabs) */
@Composable
fun MusicScreen() {
    WebRoomScreen(pageUrl = "$BASE/audio.html")
}
