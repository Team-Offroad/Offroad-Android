package com.teamoffroad.feature.diary.presentation.model

import com.teamoffroad.feature.diary.domain.model.HexCode
import com.teamoffroad.feature.diary.domain.model.MemoryLight
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class DiaryUiState(
    val diaryShown: DiaryShownState = DiaryShownState.DiaryUnShown,
    val dailyHexCodes: Map<String, List<HexCode>>? = mapOf("" to emptyList()),
    val tutorialChecked: Boolean? = null,
    val dialogVisibility: DiaryHintDialogState = DiaryHintDialogState.HintDialogInVisible,
    val diaryCreateTimeChecked: Boolean? = null,
    val timeSettingDialogVisibility: Boolean = false,
    val memoryLigthVisibility: Boolean = false,
    val memoryLightList: List<MemoryLight> = emptyList(),
    val bottomSheetVisibility: Boolean = false,
    val diaryFirstCreatedDate: Pair<Int, Int> = Pair(-1, -1),
    val currentDiaryCalendarPage: String = LocalDate.now()
        .format(DateTimeFormatter.ofPattern("yyyy년 M월"))
)

sealed interface DiarySideEffect {
    data object Empty : DiarySideEffect
    data object NavigateBack : DiarySideEffect
    data object NavigateDiaryTime : DiarySideEffect
}