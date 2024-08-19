package com.teamoffroad.feature.explore.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestsRequestDto(
    @SerialName("isActive")
    val isActive: Boolean,
)
