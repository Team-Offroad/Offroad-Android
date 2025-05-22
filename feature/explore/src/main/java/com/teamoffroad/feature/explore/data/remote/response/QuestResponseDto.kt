package com.teamoffroad.feature.explore.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestResponseDto(
    @SerialName("questName")
    val questName: String,
    @SerialName("description")
    val description: String,
    @SerialName("currentCount")
    val currentCount: Int,
    @SerialName("totalCount")
    val totalCount: Int,
    @SerialName("requirement")
    val requirement: String,
    @SerialName("reward")
    val reward: String,
    @SerialName("cursorId")
    val cursorId: Long,
    @SerialName("isCourse")
    val isCourse: Boolean,
    @SerialName("deadline")
    val deadline: String?,
    @SerialName("courseQuestPlaces")
    val courseQuestPlaces: List<CourseQuestPlaceDto>?,
    @SerialName("questId")
    val questId: Long,
)
