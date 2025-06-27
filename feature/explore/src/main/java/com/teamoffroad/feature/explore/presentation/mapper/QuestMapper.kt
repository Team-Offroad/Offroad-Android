package com.teamoffroad.feature.explore.presentation.mapper

import com.teamoffroad.core.common.domain.model.PlaceCategory
import com.teamoffroad.feature.explore.domain.model.CourseQuestPlace
import com.teamoffroad.feature.explore.presentation.model.CourseQuestPlaceUiModel

fun CourseQuestPlace.toUi(): CourseQuestPlaceUiModel =
    CourseQuestPlaceUiModel(
        category = PlaceCategory.fromKrName(category),
        name = name,
        address = address,
        position = position,
        isVisited = isVisited,
        categoryImage = categoryImage,
        description = description,
        placeId = placeId,
    )
