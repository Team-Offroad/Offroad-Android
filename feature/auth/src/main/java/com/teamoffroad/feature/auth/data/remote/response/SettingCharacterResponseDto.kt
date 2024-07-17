package com.teamoffroad.feature.auth.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SettingCharacterResponseDto(
    @SerialName("characterImageUrl")
    val characterImageUrl: String,
)
