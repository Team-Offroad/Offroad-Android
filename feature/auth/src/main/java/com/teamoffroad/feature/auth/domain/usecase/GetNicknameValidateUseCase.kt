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
        val koreanRegex = Regex("[가-힣]")
        val englishOrDigitRegex = Regex("[a-zA-Z0-9]")
        val vowelRegex = Regex("[ㅏ-ㅣ]")
        val digitRegex = Regex("[0-9]")

        var totalLength = 0
        var containsKorean = false
        var containsEnglishOrDigit = false
        val consonantRegex = Regex("[ㄱ-ㅎ]")

        for (char in text) {
            totalLength += when {
                koreanRegex.matches(char.toString()) -> {
                    containsKorean = true
                    MIN_LENGTH_KOR_NICKNAME
                }

                englishOrDigitRegex.matches(char.toString()) -> {
                    containsEnglishOrDigit = true
                    1
                }

                else -> 0
            }

            if (consonantRegex.matches(char.toString()) || vowelRegex.matches(char.toString()) || digitRegex.matches(char.toString())) {
                return false
            }

            if (totalLength > MAX_LENGTH_NICKNAME) return false
        }

        return when {
            containsKorean && !containsEnglishOrDigit -> totalLength in MIN_LENGTH_ENG_NICKNAME..MAX_LENGTH_NICKNAME
            containsEnglishOrDigit && !containsKorean -> totalLength in MIN_LENGTH_KOR_NICKNAME..MAX_LENGTH_NICKNAME
            else -> totalLength in MIN_LENGTH_KOR_NICKNAME..MAX_LENGTH_NICKNAME
        }
    }

    companion object {
        const val MIN_LENGTH_KOR_NICKNAME = 2
        const val MIN_LENGTH_ENG_NICKNAME = 4
        const val MAX_LENGTH_NICKNAME = 16
    }
}