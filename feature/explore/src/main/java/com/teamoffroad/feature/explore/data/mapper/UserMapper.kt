package com.teamoffroad.feature.explore.data.mapper

import com.teamoffroad.feature.explore.data.remote.response.ExploreLocationAuthResponseDto
import com.teamoffroad.feature.explore.domain.model.ExploreLocationResult

fun ExploreLocationAuthResponseDto.toDomain(): ExploreLocationResult {
    return ExploreLocationResult(
        isValidPosition = isValidPosition,
        successCharacterImageUrl = successCharacterImageUrl,
        completeQuests = completeQuestList?.map { it.name } ?: emptyList(),
    )
}
