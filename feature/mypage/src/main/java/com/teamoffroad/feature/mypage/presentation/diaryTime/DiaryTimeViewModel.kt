package com.teamoffroad.feature.mypage.presentation.diaryTime

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.mypage.domain.usecase.PatchDiaryCreateTimeUseCase
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
    private val diaryCreateTimeUseCase: PatchDiaryCreateTimeUseCase,
) : ViewModel() {
    private val _diaryTimeUiState: MutableStateFlow<DiaryTimeUiState> =
        MutableStateFlow(DiaryTimeUiState())
    val diaryTimeUiState: StateFlow<DiaryTimeUiState> = _diaryTimeUiState.asStateFlow()

    private val _diaryTimeSideEffect: Channel<DiaryTimeSideEffect> = Channel()
    val diaryTimeSideEffect = _diaryTimeSideEffect.receiveAsFlow()

    fun updateDialogVisibility(state: Boolean) {
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

    fun updateDiaryTime(type: Boolean, time: String) {
        if (type) {
            viewModelScope.launch {
                _diaryTimeUiState.value = diaryTimeUiState.value.copy(
                    diaryTime = time.trim()
                )
            }
        } else {
            viewModelScope.launch {
                _diaryTimeUiState.value = diaryTimeUiState.value.copy(
                    meridiem = time
                )
            }
        }
    }

    fun patchDiaryCreateTime() {
        viewModelScope.launch {
            runCatching {
                diaryCreateTimeUseCase.invoke(
                    _diaryTimeUiState.value.diaryTime.toInt(),
                    _diaryTimeUiState.value.meridiem,
                )
            }
        }
    }

    fun navigateToSetting() {
        viewModelScope.launch {
            _diaryTimeSideEffect.send(DiaryTimeSideEffect.NavigateSetting)
        }
    }
}