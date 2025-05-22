package com.teamoffroad.feature.explore.data.mapper

import com.teamoffroad.feature.explore.data.remote.response.CourseQuestPlaceDto
import com.teamoffroad.feature.explore.data.remote.response.PlaceResponseDto
import com.teamoffroad.feature.explore.domain.model.CourseQuestPlace
import com.teamoffroad.feature.explore.domain.model.Place

fun PlaceResponseDto.toDomain(): Place =
    Place(
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

fun CourseQuestPlaceDto.toDomain(): CourseQuestPlace =
    CourseQuestPlace(
        category = category,
        name = name,
        address = address,
        latitude = latitude,
        longitude = longitude,
        isVisited = isVisited,
        categoryImage = categoryImage,
    )
