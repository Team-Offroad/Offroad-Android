package com.teamoffroad.feature.diary.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryTimeBottomSheet(
    modifier: Modifier = Modifier,
    diaryTitleClick: (Boolean) -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = { diaryTitleClick(false) }) {
    }
}