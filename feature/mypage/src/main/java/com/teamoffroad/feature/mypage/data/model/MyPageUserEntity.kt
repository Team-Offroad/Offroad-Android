package com.teamoffroad.feature.mypage.data.model

data class MyPageUserEntity(
    val nickname: String,
    val currentEmblem: String,
    val elapsedDay: Int,
    val completeQuestCount: Int,
    val visitedPlaceCount: Int
)
