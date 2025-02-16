package com.teamoffroad.feature.mypage.presentation.diaryTime

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
class DiaryTimeViewModel @Inject constructor(
) : ViewModel() {
    private val _diaryTimeUiState: MutableStateFlow<DiaryTimeUiState> =
        MutableStateFlow(DiaryTimeUiState())
    val diaryTimeUiState: StateFlow<DiaryTimeUiState> = _diaryTimeUiState.asStateFlow()

    private val _diaryTimeSideEffect: Channel<DiaryTimeSideEffect> = Channel()
    val diaryTimeSideEffect = _diaryTimeSideEffect.receiveAsFlow()

    fun backButtonClickListener(state: Boolean) {
        viewModelScope.launch {
            _diaryTimeUiState.value = diaryTimeUiState.value.copy(
                dialogVisibility = if (state) DiaryTimeDialogState.BackDialogVisible else DiaryTimeDialogState.InVisible
            )
        }
    }

    fun nextButtonClickListener(state: Boolean) {
        viewModelScope.launch {
            _diaryTimeUiState.value = diaryTimeUiState.value.copy(
                dialogVisibility = if (state) DiaryTimeDialogState.NextDialogVisible else DiaryTimeDialogState.InVisible
            )
        }
    }

    fun updateDiaryTime(time: String) {
        viewModelScope.launch {
            _diaryTimeUiState.value = diaryTimeUiState.value.copy(
                diaryTime = time
            )
        }
    }

    fun navigateToSetting() {
        viewModelScope.launch {
            _diaryTimeSideEffect.send(DiaryTimeSideEffect.NavigateSetting)
        }
    }
}