package com.teamoffroad.feature.explore.data.mapper

import com.teamoffroad.feature.explore.data.remote.response.QuestResponseDto
import com.teamoffroad.feature.explore.domain.model.Quest

fun QuestResponseDto.toDomain(): Quest {
    return Quest(
        questName = questName,
        description = description,
        currentCount = currentCount,
        totalCount = totalCount,
        requirement = requirement,
        reward = reward,
    )
}