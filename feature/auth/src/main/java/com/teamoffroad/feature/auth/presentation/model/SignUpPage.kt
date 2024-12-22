package com.teamoffroad.feature.auth.presentation.model

enum class SignUpPage {
    NICKNAME,
    BIRTHDATE,
    GENDER,
    ELSE,
    ;

    companion object {
        fun Int.toSignUpPage(): SignUpPage {
            return when (this) {
                0 -> NICKNAME
                1 -> BIRTHDATE
                2 -> GENDER
                else -> ELSE
            }
        }
    }
}