package com.teamoffroad.feature.recommendplace.data.mapper

import com.teamoffroad.feature.recommendplace.data.model.PlaceRecommendationsEntity
import com.teamoffroad.feature.recommendplace.data.model.PlaceRecommendationsEntity.RecommendationsEntity
import com.teamoffroad.feature.recommendplace.data.model.PlaceRecommendationsFixedPhraseEntity
import com.teamoffroad.feature.recommendplace.data.model.PlaceRecommendationsOrderChatEntity
import com.teamoffroad.feature.recommendplace.data.remote.request.PlaceRecommendationsOrderChatRequestDto
import com.teamoffroad.feature.recommendplace.data.remote.request.PlaceRecommendationsOrderRequestDto
import com.teamoffroad.feature.recommendplace.data.remote.response.PlaceRecommendationsFixedPhraseResponseDto
import com.teamoffroad.feature.recommendplace.data.remote.response.PlaceRecommendationsOrderChatResponseDto
import com.teamoffroad.feature.recommendplace.data.remote.response.PlaceRecommendationsResponseDto
import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendations
import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendations.Recommendations
import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendationsFixedPhrase
import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendationsOrder
import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendationsOrderChat
import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendationsOrderChatRequest

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
                categoryImageUrl = place.categoryImageUrl,
                visitCount = place.visitCount
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
                categoryImageUrl = place.categoryImageUrl,
                visitCount = place.visitCount
            )
        }
    )
}

fun PlaceRecommendationsOrder.toData(): PlaceRecommendationsOrderRequestDto {
    return PlaceRecommendationsOrderRequestDto(
        recommendationType = this.recommendationType,
        region = this.region,
        additionalContent = this.additionalContent,
    )
}

fun PlaceRecommendationsOrderChatRequest.toData(): PlaceRecommendationsOrderChatRequestDto {
    return PlaceRecommendationsOrderChatRequestDto(
        content = content
    )
}

fun PlaceRecommendationsOrderChatResponseDto.toData(): PlaceRecommendationsOrderChatEntity {
    return PlaceRecommendationsOrderChatEntity(
        content = content,
        success = success
    )
}

fun PlaceRecommendationsOrderChatEntity.toDomain(): PlaceRecommendationsOrderChat {
    return PlaceRecommendationsOrderChat(
        content = content,
        success = success
    )
}

fun PlaceRecommendationsFixedPhraseResponseDto.toData(): PlaceRecommendationsFixedPhrase {
    return PlaceRecommendationsFixedPhrase(
        content = content
    )
}