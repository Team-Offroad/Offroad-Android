package com.teamoffroad.feature.auth.presentation.component

sealed interface ValidateResult {
    data object Empty : ValidateResult
    data object Error : ValidateResult
    data object ValidateSuccess : ValidateResult
    data object ValidateFailure : ValidateResult
    data object Duplicate : ValidateResult
    data object Success : ValidateResult
}

