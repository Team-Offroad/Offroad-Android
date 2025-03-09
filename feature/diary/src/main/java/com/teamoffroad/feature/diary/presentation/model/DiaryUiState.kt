package com.teamoffroad.feature.diary.presentation.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class DiaryUiState(
    val latestDiary: List<String> = emptyList(),
    val dailyHexCodes: Map<Int, List<String>> = mapOf(0 to emptyList()),
    val tutorialChecked: Boolean? = null,
    val dialogVisibility: DiaryHintDialogState = DiaryHintDialogState.HintDialogInVisible,
    val diaryCreateTimeChecked: Boolean? = null,
    val timeSettingDialogVisibility: Boolean = false,
    val memoryLigthVisibility: String? = null,
    val memoryLightList: List<MemoryLight> = emptyList(),
    val bottomSheetVisibility: Boolean = false,
    val diaryFirstCreatedDate: Pair<Int, Int> = Pair(0, 0),
    val currentDiaryCalendarPage: String = LocalDate.now()
        .format(DateTimeFormatter.ofPattern("yyyy년 MM월"))
)

sealed interface DiarySideEffect {
    data object Empty : DiarySideEffect
    data object NavigateBack : DiarySideEffect
    data object NavigateDiaryTime : DiarySideEffect
}