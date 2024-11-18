package com.teamoffroad.characterchat.presentation.model

import com.teamoffroad.characterchat.presentation.model.ChatType.USER
import com.teamoffroad.characterchat.presentation.model.TimeType.AM
import java.time.LocalDate
import java.time.LocalDateTime

data class ChatModel(
    val chatType: ChatType = USER,
    val text: String = "",
    val date: LocalDate = LocalDate.now(),
    val time: Triple<TimeType, Int, Int> = Triple(AM, 0, 0),
) {
    companion object {
        fun String.toDate(): LocalDate {
            return LocalDateTime.parse(this).toLocalDate()
        }

        fun String.toTime(): Triple<TimeType, Int, Int> {
            val localDateTime = LocalDateTime.parse(this)
            val timeType = TimeType.toTimeType(localDateTime.hour)
            val formattedHour = if (timeType == AM) localDateTime.hour else localDateTime.hour - 12

            return Triple(timeType, formattedHour, localDateTime.minute)
        }
    }
}
