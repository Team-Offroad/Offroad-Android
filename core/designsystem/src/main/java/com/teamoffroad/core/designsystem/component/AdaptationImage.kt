package com.teamoffroad.core.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
    val model = ImageRequest.Builder(LocalContext.current)
        .data(imageUrl)
        .apply {
            when {
                imageUrl.endsWith(".svg", ignoreCase = true) -> SvgDecoder.Factory()
                imageUrl.endsWith(".gif", ignoreCase = true) -> GifDecoder.Factory()
            }
        }
        .build()

    AsyncImage(
        model = model,
        contentDescription = contentDescription,
        modifier = modifier,
    )
}
