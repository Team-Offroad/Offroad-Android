package com.teamoffroad.feature.auth.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInInfoRequestDto(
    @SerialName("socialPlatform")
    val socialPlatform: String,
    @SerialName("name")
    val name: String? = null,
    @SerialName("code")
    val code: String,
)
