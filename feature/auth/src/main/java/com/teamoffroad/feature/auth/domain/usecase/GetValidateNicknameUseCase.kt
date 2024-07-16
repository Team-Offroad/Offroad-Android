package com.teamoffroad.feature.auth.domain.usecase

import javax.inject.Inject

class GetValidateNicknameUseCase @Inject constructor() {
    operator fun invoke(nickname: String): Boolean {
        return when {
            nickname.all { it.isKorean() } -> nickname.length in 2..8
            nickname.all { it.isEnglish() } -> nickname.length in 2..16
            else -> false
        }
    }

    private fun Char.isKorean(): Boolean {
        return this in '\uAC00'..'\uD7A3' || this in '\u1100'..'\u11FF' || this in '\u3130'..'\u318F'
    }

    private fun Char.isEnglish(): Boolean {
        return this in 'A'..'Z' || this in 'a'..'z'
    }
}