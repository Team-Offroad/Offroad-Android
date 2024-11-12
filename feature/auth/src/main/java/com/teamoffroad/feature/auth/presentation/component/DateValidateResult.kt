package com.teamoffroad.feature.auth.presentation.component

sealed interface DateValidateResult {
    data object Success : DateValidateResult
    data object Empty : DateValidateResult
    data object Error : DateValidateResult
}