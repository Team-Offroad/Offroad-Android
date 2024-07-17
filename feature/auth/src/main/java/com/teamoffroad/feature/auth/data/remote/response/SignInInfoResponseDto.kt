package com.teamoffroad.feature.auth.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInInfoResponseDto(
    @SerialName("tokens")
    val tokens: UserTokenResponseDto,
    @SerialName("isAlreadyExist")
    val isAlreadyExist: Boolean,
)
