package com.teamoffroad.feature.mypage.presentation.diaryTime

data class DiaryTimeUiState(
    val diaryTime: String = "",
    val meridiem: String = "",
    val dialogVisibility: DiaryTimeDialogState = DiaryTimeDialogState.InVisible,
)

sealed interface DiaryTimeSideEffect {
    data object NavigateSetting : DiaryTimeSideEffect
}