package com.teamoffroad.feature.auth.domain.usecase

import com.teamoffroad.feature.auth.presentation.model.NicknameValidateResult

class GetNicknameValidateUseCase {
    operator fun invoke(nickname: String): NicknameValidateResult {
        if (nickname.isBlank()) return NicknameValidateResult.Empty

        return when (checkInValidNickname(nickname)) {
            true -> NicknameValidateResult.NicknameValidateSuccess
            false -> NicknameValidateResult.NicknameValidateFailure
        }
    }


    private fun checkInValidNickname(text: String): Boolean {
        val koreanRegex = Regex("^[가-힣0-9]*$")
        val englishRegex = Regex("^[a-zA-Z0-9]*$")

        return when {
            koreanRegex.matches(text) -> text.length in MIN_LENGTH_NICKNAME..MAX_LENGTH_KOR_NICKNAME
            englishRegex.matches(text) -> text.length in MIN_LENGTH_NICKNAME..MAX_LENGTH_ENG_NICKNAME
            else -> false
        }
    }

    companion object {
        const val MIN_LENGTH_NICKNAME = 2
        const val MAX_LENGTH_KOR_NICKNAME = 8
        const val MAX_LENGTH_ENG_NICKNAME = 16
    }
}