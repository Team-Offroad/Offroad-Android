package com.teamoffroad.feature.mypage.domain.model

data class Announcement(
    val title: String,
    val content: String,
    val isImportant: Boolean,
    val updateAt: String,
)
