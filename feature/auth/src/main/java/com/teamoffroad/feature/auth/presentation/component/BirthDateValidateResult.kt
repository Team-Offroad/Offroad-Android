package com.teamoffroad.feature.auth.presentation.component

sealed interface BirthDateValidateResult {
    data object Success : BirthDateValidateResult
    data object Empty : BirthDateValidateResult
    data object Error : BirthDateValidateResult
    data object YearError : BirthDateValidateResult
    data object MonthError : BirthDateValidateResult
    data object DayError : BirthDateValidateResult
}