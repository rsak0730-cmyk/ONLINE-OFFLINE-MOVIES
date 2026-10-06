package com.syncbeat.app.ui.screens

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syncbeat.app.ui.components.NeuCard
import com.syncbeat.app.ui.components.NeuSectionHeader
import com.syncbeat.app.ui.components.ThemePreviewSwatch
import com.syncbeat.app.ui.theme.AppThemes
import com.syncbeat.app.ui.theme.LocalAppTheme

@Composable
fun ProfileScreen(onThemeSelected: (String) -> Unit) {
    val theme = LocalAppTheme.current
    val scroll = rememberScrollState()
    val themes = AppThemes.all
    val rows = themes.chunked(3)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(theme.background)
            .verticalScroll(scroll)
            .padding(bottom = 32.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(theme.primary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = theme.onPrimary,
                    modifier = Modifier.size(52.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "SyncBeat",
                color = theme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp
            )
            Text(
                text = "Online + Offline Movies",
                color = theme.muted,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "v2.0.0 · Neumorphism Edition",
                color = theme.primary,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        NeuSectionHeader(
            title = "Themes",
            subtitle = "Main: Neumorphism · ${themes.size} real themes · tap to switch"
        )

        NeuCard(
            modifier = Modifier.padding(horizontal = 16.dp),
            cornerRadius = 24.dp,
            contentPadding = PaddingValues(16.dp)
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Palette,
                        contentDescription = null,
                        tint = theme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = "Current: ${theme.name}",
                        color = theme.onSurface,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                }

                rows.forEach { rowThemes ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        rowThemes.forEach { t ->
                            ThemePreviewSwatch(
                                theme = t,
                                selected = t.id == theme.id,
                                onClick = { onThemeSelected(t.id) }
                            )
                        }
                        // pad incomplete last row
                        repeat(3 - rowThemes.size) {
                            Spacer(modifier = Modifier.size(76.dp))
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        NeuSectionHeader(title = "About", subtitle = "App info & features")

        NeuCard(
            modifier = Modifier.padding(horizontal = 16.dp),
            cornerRadius = 20.dp
        ) {
            InfoRow(
                icon = Icons.Default.Movie,
                title = "Features",
                body = "Online hub · Movies · Offline player (ExoPlayer) · Watch party rooms · Theme engine"
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        NeuCard(
            modifier = Modifier.padding(horizontal = 16.dp),
            cornerRadius = 20.dp
        ) {
            InfoRow(
                icon = Icons.Default.Info,
                title = "About",
                body = "Watch movies online, play local files offline, and create watch parties with friends. Built for the SyncBeat experience."
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        NeuCard(
            modifier = Modifier.padding(horizontal = 16.dp),
            cornerRadius = 20.dp
        ) {
            InfoRow(
                icon = Icons.Default.Palette,
                title = "UI Design",
                body = "Modern neumorphic design with ${themes.size} real themes. Soft raised buttons, dual shadows, Material 3 base."
            )
        }
    }
}

@Composable
private fun InfoRow(icon: ImageVector, title: String, body: String) {
    val theme = LocalAppTheme.current
    Row(verticalAlignment = Alignment.Top) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(theme.primary.copy(alpha = 0.12f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = theme.primary,
                modifier = Modifier.size(22.dp)
            )
        }
        Spacer(modifier = Modifier.size(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                color = theme.onSurface,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = body,
                color = theme.muted,
                fontSize = 13.sp,
                lineHeight = 18.sp
            )
        }
    }
}
