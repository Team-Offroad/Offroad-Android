package com.teamoffroad.feature.auth.domain.model

data class SignInInfo(
    val tokens: UserToken,
    val isAlreadyExist: Boolean,
)
