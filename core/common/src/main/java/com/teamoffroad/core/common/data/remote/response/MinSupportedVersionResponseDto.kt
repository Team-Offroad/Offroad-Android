package com.teamoffroad.core.common.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MinSupportedVersionResponseDto(
    @SerialName("ios")
    val ios: String,

    @SerialName("android")
    val android: String
)
