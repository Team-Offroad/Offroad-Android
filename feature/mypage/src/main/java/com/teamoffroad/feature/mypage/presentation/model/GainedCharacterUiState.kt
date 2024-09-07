package com.teamoffroad.feature.mypage.presentation.model

sealed interface GainedCharacterUiState {
    data object Loading : GainedCharacterUiState
    data class Success(val characters: List<CharacterModel>) : GainedCharacterUiState
    data class Error(val message: String) : GainedCharacterUiState
}
