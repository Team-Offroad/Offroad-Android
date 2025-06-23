package com.teamoffroad.feature.explore.data.mapper

import com.teamoffroad.feature.explore.data.remote.response.CourseQuestPlaceResponseDto
import com.teamoffroad.feature.explore.data.remote.response.QuestResponseDto
import com.teamoffroad.feature.explore.domain.model.CourseQuestPlace
import com.teamoffroad.feature.explore.domain.model.Quest
import com.teamoffroad.feature.explore.domain.model.Quest.CourseQuestInfo
import com.teamoffroad.feature.explore.domain.model.Quest.QuestProgressModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun QuestResponseDto.toDomain(): Quest {
    val formatter = DateTimeFormatter.ISO_DATE_TIME
    val deadlineDateTime = deadline?.let { LocalDateTime.parse(deadline, formatter) } ?: LocalDateTime.now()

    return Quest(
        questId = questId,
        questName = questName,
        description = description,
        requirement = requirement,
        reward = reward,
        cursorId = cursorId,
        progress =
            QuestProgressModel(
                currentCount = currentCount,
                totalCount = totalCount,
            ),
        courseQuestInfo =
            CourseQuestInfo(
                isCourse = isCourse,
                deadline = deadlineDateTime,
                courseQuestPlaces = courseQuestPlaces?.map { it.toDomain() } ?: emptyList(),
            ),
    )
}

fun CourseQuestPlaceResponseDto.toDomain(): CourseQuestPlace =
    CourseQuestPlace(
        category = category,
        name = name,
        address = address,
        latitude = latitude,
        longitude = longitude,
        isVisited = isVisited ?: false,
        categoryImage = categoryImage,
        description = description,
    )
