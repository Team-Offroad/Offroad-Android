package com.teamoffroad.feature.explore.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class PlaceResponseDto(
    val id: Int,
    val name: String,
    val address: String,
    val shortIntroduction: String,
    val placeCategory: String,
    val latitude: Double,
    val longitude: Double,
    val visitCount: Int,
)
