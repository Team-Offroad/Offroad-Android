package com.teamoffroad.feature.diary.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
) : ViewModel() {
    private val _diaryUiState: MutableStateFlow<DiaryUiState> =
        MutableStateFlow(DiaryUiState())
    val diaryUiState: StateFlow<DiaryUiState> = _diaryUiState.asStateFlow()

    private val _diarySideEffect: Channel<DiarySideEffect> = Channel()
    val diarySideEffect = _diarySideEffect.receiveAsFlow()

    fun getLatestDiary() {
        val dummyList = listOf("january", "february", "wednesday")
        //TODO. api/diary/latest 리스트가 비어있으면 달력 empty, 리스트가 있으면 달력 한번이라도 작성완료
        viewModelScope.launch {
            _diaryUiState.value = diaryUiState.value.copy(
                latestDiary = dummyList
            )
        }
    }

    fun getDummyHexCode() {
        val dummyHexCodes: Map<Int, List<String>> = mapOf(
            1 to listOf("#5580FF", "#FF69E1"),
            2 to listOf("#70DAFF", "#FFDC14"),
            3 to listOf("#FF69E1", "#FFB73B"),
            4 to listOf("#FF4124", "#FF6D94"),
            5 to listOf("#5580FF", "#FF69E1"),
            6 to listOf("#5580FF", "#FF69E1"),
            7 to listOf("#5580FF", "#FF69E1", "#FFFFFF"),
            9 to listOf("#5580FF", "#FF69E1", "#FFFFFF"),
            10 to listOf("#5580FF", "#FF69E1", "#FFFFFF"),
            11 to listOf("#5580FF", "#FF69E1", "#FFFFFF"),
            12 to listOf("#5580FF", "#FF69E1", "#FFFFFF"),
            13 to listOf("#5580FF", "#FF69E1", "#FFFFFF"),
            14 to listOf("#5580FF", "#FF69E1", "#FFFFFF"),
            15 to listOf("#5580FF", "#FF69E1", "#FFFFFF"),
            16 to listOf("#5580FF", "#FF69E1", "#FFFFFF"),
            17 to listOf("#5580FF", "#FF69E1", "#FFFFFF"),
            18 to listOf("#5580FF", "#FF69E1", "#FFFFFF"),
            26 to listOf("#FF69E1", "#FFB73B", "#FFFFFF"),
            27 to listOf("#FF69E1", "#FFB73B", "#FFFFFF"),
        )
        val firstDiaryMonth = 5
        viewModelScope.launch {
            _diaryUiState.value = diaryUiState.value.copy(
                dailyHexCodes = dummyHexCodes,
                firstDiaryMonth = firstDiaryMonth,
            )
        }
    }

    fun updateNavigationBackState() {
        viewModelScope.launch {
            _diarySideEffect.send(DiarySideEffect.NavigateBack)
        }
    }

    fun updateNavigationDiaryTime() {
        viewModelScope.launch {
            _diarySideEffect.send(DiarySideEffect.NavigateDiaryTime)
        }
    }

    fun updateHintDialogState(state: Boolean) {
        viewModelScope.launch {
            _diaryUiState.value = diaryUiState.value.copy(
                dialogVisibility = if (state) DiaryHintDialogState.HintDialogVisible else DiaryHintDialogState.HintDialogInVisible
            )
        }
    }

    fun updateTimeSettingDialogState(state: Boolean) {
        viewModelScope.launch {
            _diaryUiState.value = diaryUiState.value.copy(
                timeSettingDialogVisibility = state
            )
        }
    }
}
