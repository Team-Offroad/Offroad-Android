package com.teamoffroad.feature.explore.data.mapper

import com.teamoffroad.feature.explore.data.remote.response.PlaceResponseDto
import com.teamoffroad.feature.explore.domain.model.Place

fun PlaceResponseDto.toDomain(): Place {
    return Place(
        id = id,
        name = name,
        address = address,
        shortIntroduction = shortIntroduction,
        placeCategory = placeCategory,
        placeArea = placeArea,
        categoryImageUrl = categoryImageUrl,
        latitude = latitude,
        longitude = longitude,
        visitCount = visitCount,
        distanceFromUser = distanceFromUser,
    )
}
