package com.teamoffroad.feature.mypage.data.mapper

import com.teamoffroad.feature.mypage.data.remote.response.AnnouncementData
import com.teamoffroad.feature.mypage.domain.model.Announcement

fun AnnouncementData.toAnnouncement() =
    Announcement(
        title = title,
        content = content,
        isImportant = isImportant,
        updateAt = updateAt,
        hasExternalLinks = hasExternalLinks,
        externalLinks = externalLinks,
        externalLinksTitles = externalLinksTitles,
    )