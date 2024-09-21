package com.teamoffroad.feature.mypage.domain.usecase

import android.util.Log
import com.teamoffroad.feature.mypage.domain.model.Announcement
import com.teamoffroad.feature.mypage.domain.repository.AnnouncementRepository

class GetAnnouncementUseCase(
    private val announcementRepository: AnnouncementRepository
) {
    suspend operator fun invoke(): Result<List<Announcement>?> {
        return announcementRepository.getAnnouncement()
    }
}