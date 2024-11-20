package com.teamoffroad.core.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    contentDescription: String? = null,
) {
    val context = LocalContext.current

    val imageLoader = ImageLoader.Builder(context)
        .components {
            when {
                imageUrl.endsWith(".svg") -> add(SvgDecoder.Factory())
                imageUrl.endsWith(".gif") -> add(GifDecoder.Factory())
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
        contentDescription = contentDescription,
        modifier = modifier,
    )
}
