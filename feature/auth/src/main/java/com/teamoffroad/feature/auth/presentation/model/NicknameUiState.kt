package com.teamoffroad.feature.auth.presentation.model

sealed interface NicknameUiState {
    data object Empty : NicknameUiState
    data object Duplicated : NicknameUiState
    data object UnDuplicated : NicknameUiState
}