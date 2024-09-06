package com.teamoffroad.feature.mypage.presentation.model

data class CharacterDetailUiState(
    val characterDetailModel: CharacterDetailModel = CharacterDetailModel(),
    val characterMotions: List<CharacterMotionModel> = emptyList(),
    val loading: Boolean = true,
    val error: Boolean = false,
)
