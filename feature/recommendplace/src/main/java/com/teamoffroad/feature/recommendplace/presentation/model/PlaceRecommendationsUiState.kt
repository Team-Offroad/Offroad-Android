package com.teamoffroad.feature.recommendplace.presentation.model

import com.naver.maps.geometry.LatLng
import com.teamoffroad.core.common.domain.model.PlaceCategory
import com.teamoffroad.feature.explore.presentation.model.LocationModel

data class PlaceRecommendationsUiState(
    val locationModel: LocationModel = LocationModel(),
    val recommendations: List<RecommendationsUiState> = emptyList(),
    val selectedPlace: RecommendationsUiState?= null,
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val isAdditionalLoading: Boolean = false,
    val isLoadable: Boolean = true,
    val isError: Boolean = false,
) {
    data class RecommendationsUiState(
        val id: Int,
        val recommendationType: String,
        val name: String,
        val address: String,
        val shortIntroduction: String,
        val placeCategory: PlaceCategory,
        val placeArea: String,
        val latitude: Double,
        val longitude: Double,
        val categoryImageUrl: String,
        val location: LatLng,
        val visitCount: Int,
    )
}
