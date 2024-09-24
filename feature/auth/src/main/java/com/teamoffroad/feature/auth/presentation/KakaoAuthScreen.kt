package com.teamoffroad.feature.auth.presentation

import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.teamoffroad.offroad.feature.auth.BuildConfig

@Composable
fun KakaoLoginScreen(
    navigate: () -> Unit
) {
    var showWebView by remember { mutableStateOf(true) }

    if (showWebView) {
        startKakaoLoginWebView(
            clientId = BuildConfig.KAKO_CLIENT_ID,
            redirectUri = BuildConfig.KAKO_REDIRECT_URI,
            onCodeReceived = { code ->
                Log.d("asdasd", code)
            },
            onClose = {
                showWebView = false
            },
            navigate = { navigate() }
        )
    }
}

@Composable
fun startKakaoLoginWebView(
    clientId: String,
    redirectUri: String,
    onCodeReceived: (String) -> Unit,
    onClose: () -> Unit,
    navigate: () -> Unit,
) {
    val loginUrl =
        "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=$clientId&redirect_uri=$redirectUri"

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                webViewClient = object : WebViewClient() {
                    override fun onPageStarted(
                        view: WebView?,
                        url: String?,
                        favicon: android.graphics.Bitmap?
                    ) {
                        super.onPageStarted(view, url, favicon)

                        url?.let {
                            if (it.startsWith(redirectUri)) {
                                val uri = android.net.Uri.parse(it)
                                val code = uri.getQueryParameter("code")
                                if (code != null) {
                                    onCodeReceived(code)
                                    onClose()
                                }
                            }
                        }
                    }

                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        val url = request?.url.toString()
                        if (url.startsWith(redirectUri)) {
                            val uri = android.net.Uri.parse(url)
                            val code = uri.getQueryParameter("code")
                            if (code != null) {
                                onCodeReceived(code)
                                onClose()
                                navigate()
                                return true
                            }
                        }
                        return super.shouldOverrideUrlLoading(view, request)
                    }
                }
                loadUrl(loginUrl)
            }
        }
    )
}
