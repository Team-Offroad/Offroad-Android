package com.teamoffroad.feature.auth.domain.model

data class SignInInfo(
    val socialPlatform: String,
    val name: String?,
    val code: String,
)
