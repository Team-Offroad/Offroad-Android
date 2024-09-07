package com.teamoffroad.feature.mypage.presentation.model

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class CharacterDetailUiState(
    val characterDetailModel: CharacterDetailModel = CharacterDetailModel(),
    val characterMotions: ImmutableList<CharacterMotionModel> = persistentListOf(),
    val isRepresentativeUpdateSuccess: Boolean = false,
    val isLoading: Boolean = true,
    val isError: Boolean = false,
)
