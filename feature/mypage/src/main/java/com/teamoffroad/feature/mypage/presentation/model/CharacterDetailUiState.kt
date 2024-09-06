package com.teamoffroad.feature.mypage.presentation.model

data class CharacterDetailUiState(
    val characterDetailModel: CharacterDetailModel = CharacterDetailModel(),
    val characterMotions: List<CharacterMotionModel> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
)
