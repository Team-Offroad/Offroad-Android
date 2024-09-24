package com.teamoffroad.feature.mypage.data.repository

import com.teamoffroad.feature.mypage.data.mapper.toAnnouncement
import com.teamoffroad.feature.mypage.data.remote.service.AnnouncementService
import com.teamoffroad.feature.mypage.domain.model.Announcement
import com.teamoffroad.feature.mypage.domain.repository.AnnouncementRepository
import javax.inject.Inject

class AnnouncementRepositoryImpl @Inject constructor(
    private val announcementService: AnnouncementService
) : AnnouncementRepository {

    override suspend fun getAnnouncement(): Result<List<Announcement>> {
        val announcementResult = runCatching { announcementService.getAnnouncement().data }
        announcementResult.onSuccess { announcement ->
            val announcementList = announcement!!.announcements.map {
                it.toAnnouncement()
            }
            return Result.success(announcementList)
        }
        announcementResult.onFailure {
            return Result.failure(it)
        }
        return Result.failure(Exception())
    }
}