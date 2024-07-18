package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
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
    Spacer(modifier = Modifier.size(72.dp))
}
