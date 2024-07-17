package com.teamoffroad.feature.explore.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExploreAuthResponseDto(
    @SerialName("isQRMatched")
    val isQRMatched: Boolean = false,
    @SerialName("characterImageUrl")
    val characterImageUrl: String = "",
)