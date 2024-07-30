package com.teamoffroad.feature.auth.presentation.model

sealed interface SetGenderUiState {
    data object Loading : SetGenderUiState
    data object Error : SetGenderUiState
    data class Select(val selectedGender: String) : SetGenderUiState
    data object Success : SetGenderUiState
}