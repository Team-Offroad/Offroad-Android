package com.teamoffroad.feature.mypage.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnnouncementResponseDto(
    @SerialName("announcements")
    val announcements: List<AnnouncementData>
)

@Serializable
data class AnnouncementData(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
    @SerialName("isImportant")
    val isImportant: Boolean,
    @SerialName("updateAt")
    val updateAt: String,
    @SerialName("hasExternalLinks")
    val hasExternalLinks: Boolean,
    @SerialName("externalLinks")
    val externalLinks: List<String>,
    @SerialName("externalLinksTitles")
    val externalLinksTitles: List<String>,
)