package com.teamoffroad.feature.explore.presentation.model

data class PlaceModel(
    val id: Long,
    val name: String,
    val town: String,
    val address: String,
    val shortIntroduction: String,
    val placeCategory: PlaceCategory,
    val categoryImageUrl: String,
    val visitCount: Int,
)
