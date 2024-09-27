package com.teamoffroad.feature.explore.presentation.mapper

import com.naver.maps.geometry.LatLng
import com.teamoffroad.feature.explore.domain.model.Place
import com.teamoffroad.feature.explore.presentation.model.PlaceCategory
import com.teamoffroad.feature.explore.presentation.model.PlaceModel

fun Place.toUi(): PlaceModel {
    return PlaceModel(
        id = id,
        name = name,
        address = address,
        shortIntroduction = shortIntroduction,
        placeCategory = PlaceCategory.entries.find {
            it.name == placeCategory
        } ?: PlaceCategory.NONE,
        placeArea = placeArea,
        categoryImageUrl = categoryImageUrl,
        location = LatLng(latitude, longitude),
        visitCount = visitCount,
        isVisited = visitCount > 0,
    )
}
