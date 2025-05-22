package com.teamoffroad.feature.explore.domain.model

import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime

data class Quest(
    val questId: Long,
    val questName: String,
    val description: String,
    val requirement: String,
    val reward: String,
    val cursorId: Long,
    val progress: QuestProgressModel,
    val courseQuestInfo: CourseQuestInfo,
) {
    data class CourseQuestInfo(
        val isCourse: Boolean,
        val deadline: LocalDateTime,
        val courseQuestPlaces: List<CourseQuestPlace>,
    )

    data class QuestProgressModel(
        val currentCount: Int,
        val totalCount: Int,
    ) {
        val isCompleted: Boolean
            get() = currentCount >= totalCount
    }

    fun getLeftDayCount(current: LocalDate = LocalDate.now()): Int {
        val deadlineDate = courseQuestInfo.deadline.toLocalDate()

        val duration = Duration.between(current.atStartOfDay(), deadlineDate.atStartOfDay())
        return duration.toDays().toInt().coerceAtLeast(0)
    }
}
