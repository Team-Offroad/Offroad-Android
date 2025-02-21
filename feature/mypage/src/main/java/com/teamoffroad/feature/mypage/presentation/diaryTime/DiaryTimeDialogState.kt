package com.teamoffroad.feature.mypage.presentation.diaryTime

sealed interface DiaryTimeDialogState {
    data object InVisible : DiaryTimeDialogState
    data object BackDialogVisible : DiaryTimeDialogState
    data object NextDialogVisible : DiaryTimeDialogState
}