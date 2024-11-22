package com.teamoffroad.feature.mypage.domain.model

data class Announcement(
    val id: Int,
    val title: String,
    val content: String,
    val isImportant: Boolean,
    val updateAt: String,
    val hasExternalLinks: Boolean,
    val externalLinks: List<String>,
    val externalLinksTitles: List<String>,
)
