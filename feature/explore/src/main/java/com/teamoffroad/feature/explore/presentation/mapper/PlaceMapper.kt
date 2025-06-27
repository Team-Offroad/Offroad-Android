package com.teamoffroad.feature.explore.presentation.mapper

import com.naver.maps.geometry.LatLng
import com.teamoffroad.core.common.domain.model.PlaceCategory
import com.teamoffroad.feature.explore.domain.model.Place
import com.teamoffroad.feature.explore.presentation.model.PlaceModel

fun Place.toUi(): PlaceModel =
    PlaceModel(
        id = id,
        name = name,
        address = address,
        shortIntroduction = shortIntroduction,
        placeCategory = PlaceCategory.valueOf(placeCategory),
        placeArea = placeArea,
        categoryImageUrl = categoryImageUrl,
        location = LatLng(latitude, longitude),
        visitCount = visitCount,
        isVisited = visitCount > 0,
        distanceFromUser = distanceFromUser,
    )
