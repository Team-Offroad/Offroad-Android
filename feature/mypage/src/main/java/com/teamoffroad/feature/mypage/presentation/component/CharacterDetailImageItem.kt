package com.teamoffroad.feature.mypage.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.AdaptationImage
import com.teamoffroad.feature.mypage.presentation.model.CharacterDetailUiState

@Composable
fun CharacterDetailImageItem(uiState: CharacterDetailUiState) {
    AdaptationImage(
        imageUrl = uiState.characterDetailModel.characterBaseImageUrl,
        modifier = Modifier
            .padding(top = 120.dp)
            .padding(horizontal = 120.dp)
            .fillMaxWidth(),
    )
}