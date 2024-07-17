package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun ExploreSuccessDialogContent(
    url: String,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier
            .wrapContentSize(),
        contentScale = ContentScale.FillHeight
    )
}
