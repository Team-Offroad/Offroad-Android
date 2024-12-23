package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.AdaptationImage

@Composable
fun ExploreFailedDialogContent(
    modifier: Modifier = Modifier,
    url: String,
) {
    Box(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 88.dp)
                .align(Alignment.BottomCenter),
        ) {
            AdaptationImage(
                imageUrl = url,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 10.dp)
                    .height(166.dp),
            )
        }
    }
}
