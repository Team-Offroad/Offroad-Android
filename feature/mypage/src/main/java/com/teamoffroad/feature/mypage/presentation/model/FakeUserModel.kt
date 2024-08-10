package com.teamoffroad.feature.mypage.presentation.model

data class FakeUserModel(
    val nickname: String,
    val date: Int,
    val emblem: String,
    val acquireQuest: Int,
    val visitPlace: Int
) {
    companion object {
        val dummyUser = FakeUserModel(
            nickname = "비포장도로",
            date = 6887,
            emblem = "상수 고수 악수 박수",
            acquireQuest = 34246,
            visitPlace = 34
        )
    }
}
