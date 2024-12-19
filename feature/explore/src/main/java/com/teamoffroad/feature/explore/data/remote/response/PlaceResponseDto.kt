package com.teamoffroad.feature.explore.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceResponseDto(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("address")
    val address: String,
    @SerialName("shortIntroduction")
    val shortIntroduction: String,
    @SerialName("placeCategory")
    val placeCategory: String,
    @SerialName("placeArea")
    val placeArea: String,
    @SerialName("categoryImageUrl")
    val categoryImageUrl: String,
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("visitCount")
    val visitCount: Int,
    @SerialName("distanceFromUser")
    val distanceFromUser: Double,
)
