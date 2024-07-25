package com.teamoffroad.feature.auth.domain.usecase

import com.teamoffroad.feature.auth.presentation.component.ValidateResult

class GetNicknameValidateUseCase {
    suspend operator fun invoke(nickname: String): ValidateResult {

        when (checkMainLength(nickname)) {
            true -> return ValidateResult.ValidateSuccess
            false -> return ValidateResult.ValidateFailure
        }
    }


    private fun checkMainLength(text: String): Boolean {
        val koreanRegex = Regex("^[가-힣]*$")
        val englishRegex = Regex("^[a-zA-Z]*$")

        return when {
            koreanRegex.matches(text) -> text.length in 2..8
            englishRegex.matches(text) -> text.length in 2..16
            else -> false
        }
    }
}