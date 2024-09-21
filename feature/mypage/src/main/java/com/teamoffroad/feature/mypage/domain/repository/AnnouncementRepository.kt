package com.teamoffroad.feature.mypage.domain.repository

import com.teamoffroad.feature.mypage.domain.model.Announcement

interface AnnouncementRepository {
    suspend fun getAnnouncement(): Result<List<Announcement>?>
}