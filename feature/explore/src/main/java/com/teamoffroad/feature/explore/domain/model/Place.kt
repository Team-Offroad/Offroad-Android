package com.teamoffroad.feature.explore.domain.model

data class Place(
    val id: Long,
    val name: String,
    val address: String,
    val shortIntroduction: String,
    val placeCategory: String,
    val placeArea: String,
    val categoryImageUrl: String,
    val latitude: Double,
    val longitude: Double,
    val visitCount: Int,
    val distanceFromUser: Double,
)
