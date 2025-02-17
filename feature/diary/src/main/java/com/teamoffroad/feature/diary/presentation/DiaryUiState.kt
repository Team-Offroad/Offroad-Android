package com.teamoffroad.feature.diary.presentation

data class DiaryUiState(
    val latestDiary: List<String> = emptyList()
)

sealed interface DiarySideEffect {
    data object Empty : DiarySideEffect
    data object NavigateBack : DiarySideEffect
}