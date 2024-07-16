package com.teamoffroad.feature.home.data.mapper

import com.teamoffroad.feature.home.data.model.EmblemEntity
import com.teamoffroad.feature.home.data.model.UserQuestEntity
import com.teamoffroad.feature.home.data.remote.response.EmblemsResponseDto
import com.teamoffroad.feature.home.data.remote.response.UserQuestsResponseDto
import com.teamoffroad.feature.home.domain.model.Emblem
import com.teamoffroad.feature.home.domain.model.UserQuests

fun EmblemsResponseDto.EmblemResponseDto.toData(): EmblemEntity {
    return EmblemEntity(
        emblemCode = emblemCode,
        emblemName = emblemName
    )
}

fun EmblemEntity.toDomain(): Emblem {
    return Emblem(
        emblemCode = emblemCode,
        emblemName = emblemName
    )
}

fun UserQuestsResponseDto.RecentResponseDto.toData(): UserQuestEntity.RecentEntity {
    return UserQuestEntity.RecentEntity(
        questName = questName,
        progress = progress,
        completeCondition = completeCondition
    )
}

fun UserQuestsResponseDto.AlmostResponseDto.toData(): UserQuestEntity.AlmostEntity {
    return UserQuestEntity.AlmostEntity(
        questName = questName,
        progress = progress,
        completeCondition = completeCondition
    )
}

fun UserQuestEntity.RecentEntity.toDomain(): UserQuests.UserRecent {
    return UserQuests.UserRecent(
        questName = questName,
        progress = progress,
        completeCondition = completeCondition
    )
}

fun UserQuestEntity.AlmostEntity.toDomain(): UserQuests.UserAlmost {
    return UserQuests.UserAlmost(
        questName = questName,
        progress = progress,
        completeCondition = completeCondition
    )
}
