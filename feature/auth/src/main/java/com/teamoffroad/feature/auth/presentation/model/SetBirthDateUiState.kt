package com.teamoffroad.feature.auth.presentation.model

import com.teamoffroad.feature.auth.presentation.component.DateValidateResult

data class SetBirthDateUiState(
    val year: String = "",
    val month: String = "",
    val day: String = "",
    val date: String = "",
    val yearValidateResult: DateValidateResult = DateValidateResult.Empty,
    val monthValidateResult: DateValidateResult = DateValidateResult.Empty,
    val dayValidateResult: DateValidateResult = DateValidateResult.Empty,
)