package com.teamoffroad.feature.diary.presentation

data class DiaryUiState(
    val latestDiary: List<String> = emptyList(),
    val dailyHexCodes: Map<Int, List<String>> = mapOf(0 to emptyList()),
    val firstDiaryMonth: Int = 0,
    val dialogVisibility: DiaryHintDialogState = DiaryHintDialogState.HintDialogVisible,
)

sealed interface DiarySideEffect {
    data object Empty : DiarySideEffect
    data object NavigateBack : DiarySideEffect
}