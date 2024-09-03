package com.teamoffroad.feature.mypage.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.feature.mypage.presentation.component.AnnouncementDetailHeader

@Composable
internal fun AnnouncementDetailScreen(
    navigateToBack: () -> Unit,
) {
    Column(
        modifier = Modifier
            .background(Main1)
    ) {
        OffroadActionBar()
        NavigateBackAppBar(
            text = "설정",
            modifier = Modifier.padding(top = 20.dp)
        ) {
            navigateToBack()
        }
        AnnouncementDetailHeader("2024", "07", "31", "여러분의 의견을 들려주세요")
    }
}