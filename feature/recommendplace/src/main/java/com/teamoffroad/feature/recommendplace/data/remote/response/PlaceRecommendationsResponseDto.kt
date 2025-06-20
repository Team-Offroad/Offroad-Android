package com.teamoffroad.feature.recommendplace.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceRecommendationsResponseDto(
    @SerialName("recommendations")
    val recommendations: List<RecommendationsResponseDto>
) {
    @Serializable
    data class RecommendationsResponseDto(
        @SerialName("id")
        val id: Int,

        @SerialName("recommendationType")
        val recommendationType: String,

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

        @SerialName("latitude")
        val latitude: Double,

        @SerialName("longitude")
        val longitude: Double,

        @SerialName("categoryImageUrl")
        val categoryImageUrl: String,

        @SerialName("visitCount")
        val visitCount: Int
    )
}
