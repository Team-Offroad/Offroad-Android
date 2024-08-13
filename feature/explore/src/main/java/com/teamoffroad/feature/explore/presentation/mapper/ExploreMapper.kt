package com.teamoffroad.feature.explore.presentation.mapper

import com.naver.maps.geometry.LatLng
import com.teamoffroad.feature.explore.domain.model.Place
import com.teamoffroad.feature.explore.presentation.model.ExplorePlaceModel
import com.teamoffroad.feature.explore.presentation.model.PlaceCategory

fun Place.toUi(): ExplorePlaceModel {
    return ExplorePlaceModel(
        id = id,
        name = name,
        address = address,
        shortIntroduction = shortIntroduction,
        placeCategory = PlaceCategory.entries.find {
            it.name == placeCategory
        } ?: PlaceCategory.NONE,
        categoryImageUrl = categoryImageUrl,
        location = LatLng(latitude, longitude),
        visitCount = visitCount,
    )
}
