package com.teamoffroad.feature.recommendplace.data.mapper

import com.teamoffroad.feature.recommendplace.data.model.PlaceRecommendationsEntity
import com.teamoffroad.feature.recommendplace.data.model.PlaceRecommendationsEntity.RecommendationsEntity
import com.teamoffroad.feature.recommendplace.data.remote.response.PlaceRecommendationsResponseDto
import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendations
import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendations.Recommendations

fun PlaceRecommendationsResponseDto.toData(): PlaceRecommendationsEntity {
    return PlaceRecommendationsEntity(
        recommendations = this.recommendations.map { place ->
            RecommendationsEntity(
                id = place.id,
                recommendationType = place.recommendationType,
                name = place.name,
                address = place.address,
                shortIntroduction = place.shortIntroduction,
                placeCategory = place.placeCategory,
                placeArea = place.placeArea,
                latitude = place.latitude,
                longitude = place.longitude,
                categoryImageUrl = place.categoryImageUrl
            )
        }
    )
}

fun PlaceRecommendationsEntity.toDomain(): PlaceRecommendations {
    return PlaceRecommendations(
        recommendations = this.recommendations.map { place ->
            Recommendations(
                id = place.id,
                recommendationType = place.recommendationType,
                name = place.name,
                address = place.address,
                shortIntroduction = place.shortIntroduction,
                placeCategory = place.placeCategory,
                placeArea = place.placeArea,
                latitude = place.latitude,
                longitude = place.longitude,
                categoryImageUrl = place.categoryImageUrl
            )
        }
    )
}