package com.teamoffroad.characterchat.presentation.model

enum class TimeType(val krName: String) {
    AM("오전"), PM("오후"),
    ;

    companion object {
        fun toTimeType(hour: Int): TimeType {
            return if (hour < 12) AM else PM
        }
    }
}
