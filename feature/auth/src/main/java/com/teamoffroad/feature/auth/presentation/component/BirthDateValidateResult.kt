package com.teamoffroad.feature.auth.presentation.component

sealed interface BirthDateValidateResult {
    data object Empty : BirthDateValidateResult
    data object Error : BirthDateValidateResult
    data object Success : BirthDateValidateResult
}