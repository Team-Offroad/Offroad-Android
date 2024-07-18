package com.teamoffroad.feature.explore.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExploreQrAuthRequestDto(
    @SerialName("placeId")
    val placeId: Long,
    @SerialName("qrCode")
    val qrCode: String,
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
)
