package com.syncbeat.app.player

import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.syncbeat.app.data.ThemeManager
import com.syncbeat.app.ui.theme.AppThemes
import com.syncbeat.app.ui.theme.SyncBeatTheme

class PlayerActivity : ComponentActivity() {

    companion object {
        const val EXTRA_URI = "extra_uri"
        const val EXTRA_TITLE = "extra_title"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        val uriString = intent.getStringExtra(EXTRA_URI) ?: run {
            finish()
            return
        }
        val title = intent.getStringExtra(EXTRA_TITLE) ?: "Video"

        setContent {
            val context = LocalContext.current
            val themeManager = remember { ThemeManager(context) }
            val theme by themeManager.themeFlow.collectAsState(initial = AppThemes.NeumorphismLight)

            SyncBeatTheme(theme = theme) {
                VideoPlayerScreen(uri = Uri.parse(uriString), title = title)
            }
        }
    }
}

@Composable
fun VideoPlayerScreen(uri: Uri, title: String) {
    val context = LocalContext.current

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(uri))
            prepare()
            playWhenReady = true
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                    useController = true
                    setShowNextButton(false)
                    setShowPreviousButton(false)
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}
