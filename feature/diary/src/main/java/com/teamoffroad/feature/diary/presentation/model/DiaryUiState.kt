package com.teamoffroad.feature.diary.presentation.model

data class DiaryUiState(
    val latestDiary: List<String> = emptyList(),
    val dailyHexCodes: Map<Int, List<String>> = mapOf(0 to emptyList()),
    val firstDiaryMonth: Int = 0,
    val dialogVisibility: DiaryHintDialogState = DiaryHintDialogState.HintDialogInVisible,
    val timeSettingDialogVisibility: Boolean = false,
    val memoryLigthVisibility: String? = null,
    val memoryLightList: List<MemoryLight> = emptyList(),
    val bottomSheetVisibility: Boolean = false,
)

sealed interface DiarySideEffect {
    data object Empty : DiarySideEffect
    data object NavigateBack : DiarySideEffect
    data object NavigateDiaryTime : DiarySideEffect
}