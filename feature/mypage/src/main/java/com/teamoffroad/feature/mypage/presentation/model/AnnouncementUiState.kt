package com.teamoffroad.feature.mypage.presentation.model

import com.teamoffroad.feature.mypage.domain.model.Announcement
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class AnnouncementUiState(
    val announcementList: ImmutableList<Announcement> = emptyList<Announcement>().toImmutableList(),
    val announcementValidateResult: AnnouncementResult = AnnouncementResult.Empty,
)
