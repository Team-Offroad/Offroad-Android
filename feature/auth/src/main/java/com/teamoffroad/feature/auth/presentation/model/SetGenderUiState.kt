package com.teamoffroad.feature.auth.presentation.model

data class SetGenderUiState(
    val selectedGender: String = "",
    val genderResult: SetGenderStateResult = SetGenderStateResult.Empty
)

sealed interface SetGenderStateResult {
    data object Empty : SetGenderStateResult
    data object Loading : SetGenderStateResult
    data object Error : SetGenderStateResult
    data object Select : SetGenderStateResult
    data object Success : SetGenderStateResult
}