package com.teamoffroad.feature.explore.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseQuestPlaceDto(
    @SerialName("category")
    val category: String,
    @SerialName("name")
    val name: String,
    @SerialName("address")
    val address: String,
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("isVisited")
    val isVisited: Boolean?,
    @SerialName("categoryImage")
    val categoryImage: String,
    @SerialName("description")
    val description: String,
)
