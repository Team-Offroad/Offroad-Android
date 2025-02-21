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
        val testEmptyList = emptyList<String>()
        //TODO. api/diary/latest 리스트가 비어있으면 달력 empty, 리스트가 있으면 달력 한번이라도 작성완료
        viewModelScope.launch {
            _diaryUiState.value = diaryUiState.value.copy(
                latestDiary = testEmptyList
            )
        }
    }

    fun backButtonClickListener() {
        viewModelScope.launch {
            _diarySideEffect.send(DiarySideEffect.NavigateBack)
        }
    }
}
