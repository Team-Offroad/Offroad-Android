package com.teamoffroad.feature.explore.presentation.mapper

import com.teamoffroad.feature.explore.domain.model.Quest
import com.teamoffroad.feature.explore.presentation.model.QuestModel
import com.teamoffroad.feature.explore.presentation.model.QuestModel.QuestProgressModel

fun Quest.toUi(): QuestModel {
    return QuestModel(
        questName = questName,
        description = description,
        requirement = requirement,
        reward = reward,
        questProgressModel = progress.toUi()
    )
}

fun Quest.QuestProgressModel.toUi(): QuestProgressModel {
    return QuestProgressModel(
        progressCount = currentCount,
        totalCount = totalCount,
        isCompleted = isCompleted,
    )
}
