package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.offroad.feature.explore.R

@Composable
fun ExploreTrackingButton(
    modifier: Modifier,
    isTrackingEnabled: Boolean,
    onClick: (Boolean) -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .clickable { onClick(isTrackingEnabled) }
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .align(Alignment.Center)
                    .shadow(elevation = 2.dp, shape = CircleShape)
            )
            Image(
                painter = when (isTrackingEnabled) {
                    true -> painterResource(R.drawable.ic_explore_tracking_follow)
                    false -> painterResource(R.drawable.ic_explore_tracking_no_follow)
                },
                contentDescription = "트래킹 모드 전환",
                modifier = Modifier
                    .size(42.dp)
                    .align(Alignment.Center),
            )
        }
    }
}
