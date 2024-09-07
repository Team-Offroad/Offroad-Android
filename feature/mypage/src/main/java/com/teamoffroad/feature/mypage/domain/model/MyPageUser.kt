package com.teamoffroad.feature.mypage.domain.model

data class MyPageUser(
    val nickname: String,
    val currentEmblem: String,
    val elapsedDay: Int,
    val completeQuestCount: Int,
    val visitedPlaceCount: Int,
)
