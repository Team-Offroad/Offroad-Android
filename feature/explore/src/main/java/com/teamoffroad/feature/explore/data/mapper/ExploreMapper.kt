package com.teamoffroad.feature.explore.data.mapper

import com.teamoffroad.feature.explore.data.remote.response.ExploreLocationAuthResponseDto
import com.teamoffroad.feature.explore.data.remote.response.ExploreQrAuthResponseDto
import com.teamoffroad.feature.explore.data.remote.response.PlaceResponseDto
import com.teamoffroad.feature.explore.domain.model.ExploreLocationResult
import com.teamoffroad.feature.explore.domain.model.ExploreQrResult
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

fun ExploreQrAuthResponseDto.toDomain(): ExploreQrResult {
    return ExploreQrResult(
        isQRMatched = isQRMatched,
        characterImageUrl = characterImageUrl,
    )
}

fun ExploreLocationAuthResponseDto.toDomain(): ExploreLocationResult {
    return ExploreLocationResult(
        isValidPosition = isValidPosition,
        successCharacterImageUrl = successCharacterImageUrl,
    )
}
