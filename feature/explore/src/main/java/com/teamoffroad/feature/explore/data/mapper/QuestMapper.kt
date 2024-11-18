package com.teamoffroad.feature.explore.data.mapper

import com.teamoffroad.feature.explore.data.remote.response.QuestResponseDto
import com.teamoffroad.feature.explore.domain.model.Quest
import com.teamoffroad.feature.explore.domain.model.Quest.QuestProgressModel

fun QuestResponseDto.toDomain(): Quest {
    return Quest(
        questName = questName,
        description = description,
        requirement = requirement,
        reward = reward,
        cursorId = cursorId,
        progress = QuestProgressModel(
            currentCount = currentCount,
            totalCount = totalCount,
        )
    )
}