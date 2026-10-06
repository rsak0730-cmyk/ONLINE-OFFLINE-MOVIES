package com.syncbeat.app.ui.components

import android.annotation.SuppressLint
import android.graphics.Color as AndroidColor
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.syncbeat.app.ui.theme.LocalAppTheme

/**
 * One room = one WebView. No hub slider / web tab bar.
 * Uses ?app=1 so pages can hide attached tab interfaces.
 */
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebRoomScreen(pageUrl: String) {
    val theme = LocalAppTheme.current
    var progress by remember { mutableFloatStateOf(0f) }
    var isLoading by remember { mutableStateOf(true) }

    val finalUrl = remember(pageUrl) {
        when {
            pageUrl.contains("app=1") -> pageUrl
            pageUrl.contains("?") -> "$pageUrl&app=1"
            else -> "$pageUrl?app=1"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(theme.background)
    ) {
        if (isLoading) {
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(3.dp),
                color = theme.primary,
                trackColor = theme.border.copy(alpha = 0.3f)
            )
        }

        Box(modifier = Modifier.fillMaxSize()) {
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        setBackgroundColor(AndroidColor.TRANSPARENT)
                        settings.apply {
                            javaScriptEnabled = true
                            domStorageEnabled = true
                            databaseEnabled = true
                            mediaPlaybackRequiresUserGesture = false
                            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                            allowFileAccess = true
                            allowContentAccess = true
                            cacheMode = WebSettings.LOAD_DEFAULT
                            userAgentString = userAgentString.replace("; wv", "")
                        }
                        webViewClient = object : WebViewClient() {
                            override fun shouldOverrideUrlLoading(
                                view: WebView?,
                                request: WebResourceRequest?
                            ): Boolean = false

                            override fun onPageFinished(view: WebView?, url: String?) {
                                isLoading = false
                                view?.evaluateJavascript(
                                    """
                                    (function(){
                                      try {
                                        var bar = document.getElementById('tabBar');
                                        if (bar) { bar.style.display = 'none'; bar.remove(); }
                                        document.querySelectorAll('.tab-bar,#tabBar').forEach(function(el){
                                          el.style.display = 'none';
                                        });
                                        document.documentElement.classList.add('syncbeat-app');
                                        document.body.classList.add('syncbeat-app');
                                        document.body.style.paddingBottom = '12px';
                                      } catch(e) {}
                                    })();
                                    """.trimIndent(),
                                    null
                                )
                            }
                        }
                        webChromeClient = object : WebChromeClient() {
                            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                                progress = newProgress / 100f
                                isLoading = newProgress < 100
                            }
                        }
                        loadUrl(finalUrl)
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
