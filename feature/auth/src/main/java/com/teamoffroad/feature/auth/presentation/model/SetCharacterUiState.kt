package com.teamoffroad.feature.auth.presentation.model

sealed interface SetCharacterUiState {
    data object Loading : SetCharacterUiState
    data object Error : SetCharacterUiState
    data class Success(val characterImageUrl: String) : SetCharacterUiState
}
