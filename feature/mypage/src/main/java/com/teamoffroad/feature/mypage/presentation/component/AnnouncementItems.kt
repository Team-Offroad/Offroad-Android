package com.teamoffroad.feature.mypage.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.teamoffroad.feature.mypage.presentation.model.AnnouncementUiState

@Composable
fun AnnouncementItems(
    modifier: Modifier = Modifier,
    isAnnouncementState: AnnouncementUiState,
    onClick: (String, String, Boolean, String) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        items(isAnnouncementState.announcementList) { it ->
            SettingContainer(
                title = it.title,
                isImportant = it.isImportant,
                onClick = { onClick(it.title, it.content, it.isImportant, it.updateAt) }
            )
        }
    }
}