package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.teamoffroad.offroad.feature.explore.R

@Composable
fun ExploreSuccessDialogContent(
    modifier: Modifier = Modifier,
    url: String? = stringResource(R.string.explore_failed_img),
) {
    val context = LocalContext.current

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(url)
            .decoderFactory(SvgDecoder.Factory())
            .build(),
        contentDescription = null,
        modifier = modifier
            .height(162.dp),
        contentScale = ContentScale.FillHeight
    )
    Spacer(modifier = Modifier.size(72.dp))
}
