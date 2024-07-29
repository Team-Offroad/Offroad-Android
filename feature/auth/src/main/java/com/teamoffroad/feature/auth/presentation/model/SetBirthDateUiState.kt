package com.teamoffroad.feature.auth.presentation.model

import com.teamoffroad.feature.auth.presentation.component.BirthDateValidateResult

data class SetBirthDateUiState(
    val year: String = "",
    val month: String = "",
    val day: String = "",
    val birthDateValidateResult: BirthDateValidateResult = BirthDateValidateResult.Empty
)