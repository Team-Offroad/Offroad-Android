package com.teamoffroad.feature.mypage.presentation.model

sealed interface AnnouncementResult {
    data object Empty : AnnouncementResult
    data object Error : AnnouncementResult
    data object Success : AnnouncementResult
}