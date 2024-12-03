package com.teamoffroad.feature.auth.presentation.model

data class SetBirthDateUiState(
    val year: String = "",
    val month: String = "",
    val day: String = "",
    val date: String = "",
    val yearValidateResult: DateValidateResult = DateValidateResult.Empty,
    val monthValidateResult: DateValidateResult = DateValidateResult.Empty,
    val dayValidateResult: DateValidateResult = DateValidateResult.Empty,
)