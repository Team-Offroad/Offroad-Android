package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.AdaptationImage
import com.teamoffroad.offroad.feature.explore.R

@Composable
fun ExploreFailedDialogContent(
    modifier: Modifier = Modifier,
    imageUrl: String? = stringResource(id = R.string.explore_failed_img),
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .wrapContentWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 72.dp)
                .align(Alignment.BottomCenter),
        ) {
            AdaptationImage(
                imageUrl = imageUrl ?: "",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}
