package com.teamoffroad.feature.home.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FcmTokenRequestDto(
    @SerialName("token")
    val token: String,
)
