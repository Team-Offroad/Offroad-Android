package com.teamoffroad.feature.mypage.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.teamoffroad.feature.mypage.presentation.model.CharacterDetailUiState

@Composable
fun CharacterDetailImageItem(uiState: CharacterDetailUiState) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(uiState.characterDetailModel.characterBaseImageUrl)
            .decoderFactory(SvgDecoder.Factory())
            .build(),
        contentDescription = null,
        modifier = Modifier
            .padding(top = 120.dp)
            .padding(horizontal = 120.dp)
            .fillMaxWidth(),
    )
}