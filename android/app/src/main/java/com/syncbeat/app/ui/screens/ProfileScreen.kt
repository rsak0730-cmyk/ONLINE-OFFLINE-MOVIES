package com.syncbeat.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.syncbeat.app.ui.theme.SyncBeatBg
import com.syncbeat.app.ui.theme.SyncBeatCard
import com.syncbeat.app.ui.theme.SyncBeatMuted
import com.syncbeat.app.ui.theme.SyncBeatPrimary

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SyncBeatBg)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(96.dp)
                .clip(CircleShape)
                .background(SyncBeatPrimary)
                .padding(20.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "SyncBeat",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White
        )
        Text(
            text = "Online + Offline Movies",
            style = MaterialTheme.typography.bodyMedium,
            color = SyncBeatMuted
        )

        Spacer(modifier = Modifier.height(40.dp))

        InfoCard(
            title = "About",
            body = "Watch movies online, play local files offline, and create watch parties with friends. Built for the SyncBeat experience."
        )
        Spacer(modifier = Modifier.height(12.dp))
        InfoCard(
            title = "Version",
            body = "1.0.0  •  Hybrid WebView + Native Player"
        )
        Spacer(modifier = Modifier.height(12.dp))
        InfoCard(
            title = "Features",
            body = "• Online movie hub\n• Offline local video player (ExoPlayer)\n• Watch party rooms\n• Dark Material 3 UI"
        )
    }
}

@Composable
private fun InfoCard(title: String, body: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SyncBeatCard)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = SyncBeatPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = body,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                textAlign = TextAlign.Start
            )
        }
    }
}
