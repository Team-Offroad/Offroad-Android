package com.teamoffroad.feature.mypage.presentation.model

enum class CharacterCategory(val krLabel: String) {
    CAFE("카페 방문 시"),
    PARK("공원 방문 시"),
    RESTAURANT("식당 방문 시"),
    CULTURE("문화 방문 시"),
    SPORT("스포츠 방문 시"),
    NONE(""),
    ;

    companion object {
        fun from(value: String): CharacterCategory {
            return entries.firstOrNull { it.name == value } ?: NONE
        }
    }
}
