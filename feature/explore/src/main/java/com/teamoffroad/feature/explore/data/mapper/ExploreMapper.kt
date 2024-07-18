package com.teamoffroad.feature.explore.data.mapper

import com.teamoffroad.feature.explore.data.remote.response.ExploreAuthResponseDto
import com.teamoffroad.feature.explore.data.remote.response.PlaceResponseDto
import com.teamoffroad.feature.explore.domain.model.ExploreResult
import com.teamoffroad.feature.explore.domain.model.Place

fun PlaceResponseDto.toDomain(): Place {
    return Place(
        id = id,
        name = name,
        address = address,
        shortIntroduction = shortIntroduction,
        placeCategory = placeCategory,
        categoryImageUrl = categoryImageUrl,
        latitude = latitude,
        longitude = longitude,
        visitCount = visitCount,
    )
}

fun ExploreAuthResponseDto.toDomain(): ExploreResult {
    return ExploreResult(
        isQRMatched = isQRMatched,
        characterImageUrl = characterImageUrl,
    )
}