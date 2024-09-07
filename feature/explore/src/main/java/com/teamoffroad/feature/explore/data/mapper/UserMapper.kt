package com.teamoffroad.feature.explore.data.mapper

import com.teamoffroad.feature.explore.data.remote.response.ExploreLocationAuthResponseDto
import com.teamoffroad.feature.explore.data.remote.response.ExploreQrAuthResponseDto
import com.teamoffroad.feature.explore.domain.model.ExploreLocationResult
import com.teamoffroad.feature.explore.domain.model.ExploreQrResult

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
        completeQuests = completeQuestList?.map { it.name } ?: emptyList(),
    )
}
