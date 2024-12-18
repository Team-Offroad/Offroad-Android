package com.teamoffroad.core.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun AdaptationImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentScale: ContentScale = ContentScale.Fit,
    contentDescription: String? = null,
) {
    val context = LocalContext.current

    val isSvg = containsExtension(imageUrl, "svg")
    val isGif = containsExtension(imageUrl, "gif")

    val imageLoader = ImageLoader.Builder(context)
        .components {
            when {
                isSvg -> add(SvgDecoder.Factory())
                isGif -> add(GifDecoder.Factory())
            }
        }
        .build()

    val request = ImageRequest.Builder(context)
        .data(imageUrl)
        .crossfade(true)
        .build()

    AsyncImage(
        model = request,
        imageLoader = imageLoader,
        contentScale = contentScale,
        contentDescription = contentDescription,
        modifier = modifier,
    )
}

private fun containsExtension(url: String, extension: String): Boolean {
    val regex = Regex("\\.$extension(\\?|$)", RegexOption.IGNORE_CASE)
    return regex.containsMatchIn(url)
}
