package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teamoffroad.core.designsystem.theme.Black
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.explore.presentation.model.ExploreCameraUiState
import com.teamoffroad.offroad.feature.explore.R

@Composable
fun ExploreCameraNavigateBack(
    navigateToExplore: (String, String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Black.copy(alpha = 0.44f))
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { navigateToExplore(ExploreCameraUiState.None.toString(), "None") })
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_explore_navigate_back),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .size(48.dp)
                    .padding(12.dp)
            )
            Text(
                text = "이전 화면",
                style = OffroadTheme.typography.textRegular,
                color = White,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}