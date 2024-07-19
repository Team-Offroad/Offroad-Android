package com.teamoffroad.feature.explore.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExploreLocationAuthRequestDto(
    @SerialName("placeId")
    val placeId: Long,
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
)
