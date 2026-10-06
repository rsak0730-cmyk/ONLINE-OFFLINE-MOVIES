package com.syncbeat.app.ui.screens

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.syncbeat.app.ui.theme.LocalAppTheme

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun HomeScreen() {
    val theme = LocalAppTheme.current
    var progress by remember { mutableFloatStateOf(0f) }
    var isLoading by remember { mutableStateOf(true) }
    val hubUrl = "https://rsak0730-cmyk.github.io/ONLINE-OFFLINE-MOVIES/"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(theme.background)
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)) {
            Text(
                text = "SyncBeat",
                color = theme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Online hub · Watch together",
                color = theme.muted,
                fontSize = 13.sp
            )
        }

        if (isLoading) {
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = theme.primary,
                trackColor = theme.border.copy(alpha = 0.4f)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 4.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(theme.surfaceElevated)
        ) {
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        setBackgroundColor(android.graphics.Color.TRANSPARENT)
                        settings.apply {
                            javaScriptEnabled = true
                            domStorageEnabled = true
                            mediaPlaybackRequiresUserGesture = false
                            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                            allowFileAccess = true
                            cacheMode = WebSettings.LOAD_DEFAULT
                            userAgentString = userAgentString.replace("; wv", "")
                        }
                        webViewClient = object : WebViewClient() {
                            override fun onPageFinished(view: WebView?, url: String?) {
                                isLoading = false
                            }
                        }
                        webChromeClient = object : WebChromeClient() {
                            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                                progress = newProgress / 100f
                                isLoading = newProgress < 100
                            }
                        }
                        loadUrl(hubUrl)
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(24.dp))
            )
        }
    }
}
