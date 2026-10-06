package com.syncbeat.app.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syncbeat.app.player.PlayerActivity
import com.syncbeat.app.ui.components.NeuCard
import com.syncbeat.app.ui.components.NeuEmptyState
import com.syncbeat.app.ui.components.NeuIconButton
import com.syncbeat.app.ui.components.NeuSectionHeader
import com.syncbeat.app.ui.theme.LocalAppTheme

data class LocalVideo(
    val uri: Uri,
    val name: String
)

@Composable
fun LibraryScreen() {
    val theme = LocalAppTheme.current
    val context = LocalContext.current
    val videos = remember { mutableStateListOf<LocalVideo>() }

    val picker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let {
            try {
                context.contentResolver.takePersistableUriPermission(
                    it,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            } catch (_: Exception) { }

            val name = it.lastPathSegment?.substringAfterLast('/') ?: "Video ${videos.size + 1}"
            videos.add(LocalVideo(it, name))
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(theme.background)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NeuSectionHeader(
                title = "Offline Library",
                subtitle = "Play movies from your device — 100% offline",
                action = {
                    NeuIconButton(
                        icon = Icons.Default.Add,
                        onClick = { picker.launch(arrayOf("video/*")) },
                        primary = true,
                        size = 48.dp,
                        iconSize = 22.dp,
                        contentDescription = "Add video"
                    )
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (videos.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    NeuEmptyState(
                        icon = Icons.Default.Movie,
                        title = "No local videos yet",
                        message = "Tap + to pick video files from your phone or SD card.\nThey play fully offline with ExoPlayer."
                    )
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(videos) { video ->
                        NeuCard(
                            onClick = {
                                val intent = Intent(context, PlayerActivity::class.java).apply {
                                    putExtra(PlayerActivity.EXTRA_URI, video.uri.toString())
                                    putExtra(PlayerActivity.EXTRA_TITLE, video.name)
                                }
                                context.startActivity(intent)
                            },
                            cornerRadius = 20.dp,
                            contentPadding = PaddingValues(14.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(56.dp)
                                        .clip(RoundedCornerShape(14.dp))
                                        .background(theme.primary.copy(alpha = 0.15f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    androidx.compose.material3.Icon(
                                        imageVector = Icons.Default.PlayArrow,
                                        contentDescription = null,
                                        tint = theme.primary,
                                        modifier = Modifier.size(32.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(14.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = video.name,
                                        color = theme.onSurface,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 16.sp,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Local file · Offline ready",
                                        color = theme.muted,
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        }
                    }
                    item { Spacer(modifier = Modifier.height(72.dp)) }
                }
            }
        }
    }
}
