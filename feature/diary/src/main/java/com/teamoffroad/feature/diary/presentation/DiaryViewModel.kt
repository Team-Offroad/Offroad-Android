package com.teamoffroad.feature.diary.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.diary.domain.usecase.GetDiaryByDateUseCase
import com.teamoffroad.feature.diary.domain.usecase.GetDiaryCreateTimeCheckedUseCase
import com.teamoffroad.feature.diary.domain.usecase.GetDiaryFirstDateUseCase
import com.teamoffroad.feature.diary.domain.usecase.GetDiaryLatestUseCase
import com.teamoffroad.feature.diary.domain.usecase.GetDiaryMonthlyHexUseCase
import com.teamoffroad.feature.diary.domain.usecase.GetDiaryTutorialCheckedUseCase
import com.teamoffroad.feature.diary.domain.usecase.PatchDiaryCreateTimeCheckedUseCase
import com.teamoffroad.feature.diary.domain.usecase.PatchDiaryTutorialCheckedUseCase
import com.teamoffroad.feature.diary.presentation.model.DiaryHintDialogState
import com.teamoffroad.feature.diary.presentation.model.DiarySideEffect
import com.teamoffroad.feature.diary.presentation.model.DiaryUiState
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
    private val getDiaryTutorialCheckedUseCase: GetDiaryTutorialCheckedUseCase,
    private val patchDiaryTutorialCheckedUseCase: PatchDiaryTutorialCheckedUseCase,
    private val getDiaryCreateTimeCheckedUseCase: GetDiaryCreateTimeCheckedUseCase,
    private val patchDiaryCreateTimeCheckedUseCase: PatchDiaryCreateTimeCheckedUseCase,
    private val getDiaryFirstDateUseCase: GetDiaryFirstDateUseCase,
    private val getDiaryMonthlyHexUseCase: GetDiaryMonthlyHexUseCase,
    private val getDiaryByDateUseCase: GetDiaryByDateUseCase,
    private val getDiaryLatestUseCase: GetDiaryLatestUseCase,
) : ViewModel() {
    private val _diaryUiState: MutableStateFlow<DiaryUiState> =
        MutableStateFlow(DiaryUiState())
    val diaryUiState: StateFlow<DiaryUiState> = _diaryUiState.asStateFlow()

    private val _diarySideEffect: Channel<DiarySideEffect> = Channel()
    val diarySideEffect = _diarySideEffect.receiveAsFlow()

    init {
        updateLatestMemoryLight()
    }

    fun getDiaryFirstDate() {
        viewModelScope.launch {
            getDiaryFirstDateUseCase.invoke().onSuccess { diaryFirstDate ->
                if (diaryFirstDate != null) {
                    _diaryUiState.value = diaryUiState.value.copy(
                        diaryFirstCreatedDate = Pair(diaryFirstDate.year, diaryFirstDate.month)
                    )
                }
            }
        }
    }

    fun getLatestDiary() {
        val dummyList = listOf("january", "february", "wednesday")
        //TODO. api/diary/latest 리스트가 비어있으면 달력 empty, 리스트가 있으면 달력 한번이라도 작성완료
        viewModelScope.launch {
            _diaryUiState.value = diaryUiState.value.copy(
                latestDiary = dummyList
            )
        }
    }

    fun getDummyHexCode(year: Int, month: Int) {
        viewModelScope.launch {
            getDiaryMonthlyHexUseCase.invoke(year, month).onSuccess {
                _diaryUiState.value = diaryUiState.value.copy(
                    dailyHexCodes = it,
                )
            }
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

    fun getDiaryTutorialChecked() {
        viewModelScope.launch {
            getDiaryTutorialCheckedUseCase.invoke().onSuccess {
                _diaryUiState.value = diaryUiState.value.copy(
                    tutorialChecked = it
                )
            }
            if (diaryUiState.value.tutorialChecked == false) updateHintDialogState(true)
        }
    }

    fun patchDiaryTutorialChecked() {
        viewModelScope.launch {
            patchDiaryTutorialCheckedUseCase.invoke()
        }
    }

    fun updateHintDialogState(state: Boolean) {
        viewModelScope.launch {
            _diaryUiState.value = diaryUiState.value.copy(
                dialogVisibility = if (state) DiaryHintDialogState.HintDialogVisible else DiaryHintDialogState.HintDialogInVisible
            )
        }
    }

    fun getDiaryCreateTimeChecked() {
        viewModelScope.launch {
            getDiaryCreateTimeCheckedUseCase.invoke().onSuccess {
                _diaryUiState.value = diaryUiState.value.copy(
                    diaryCreateTimeChecked = it
                )
                updateTimeSettingDialogWithoutTutorial()
            }
        }
    }

    fun patchDiaryCreateTimeChecked() {
        viewModelScope.launch {
            patchDiaryCreateTimeCheckedUseCase.invoke()
        }
    }

    private fun updateTimeSettingDialogWithoutTutorial() {
        if (diaryUiState.value.tutorialChecked == true and (diaryUiState.value.diaryCreateTimeChecked == false)) {
            updateTimeSettingDialogState(true)
        }
    }

    fun updateTimeSettingDialogState(state: Boolean) {
        viewModelScope.launch {
            _diaryUiState.value = diaryUiState.value.copy(
                timeSettingDialogVisibility = if (diaryUiState.value.diaryCreateTimeChecked == true) false else state
            )
        }
    }

    fun updateLatestMemoryLight() {
        //TODO. 가장 최신일기 확인여부 받아오기
        viewModelScope.launch {
            getDiaryLatestUseCase.invoke(1).onSuccess {
                Log.d("asdasdasd", it.toString())
            }.onFailure {
                Log.d("asdasdasd", it.message.toString())
            }
        }
    }

    fun updateMemoryLightInfo(date: String?) {
        viewModelScope.launch {
            if (date != null) {
                getDiaryByDateUseCase.invoke(date, 1, 1).onSuccess {
                    _diaryUiState.value = diaryUiState.value.copy(
                        memoryLightList = it
                    )
                }
            }
        }
    }

    fun updateMemoryLightState(state: Boolean) {
        viewModelScope.launch {
            if (!state) {
                _diaryUiState.value = diaryUiState.value.copy(
                    memoryLightList = emptyList(),
                )
            }
            _diaryUiState.value = diaryUiState.value.copy(
                memoryLigthVisibility = state,
            )
        }
    }

    fun updateBottomSheetState(state: Boolean) {
        viewModelScope.launch {
            _diaryUiState.value = diaryUiState.value.copy(
                bottomSheetVisibility = state
            )
        }
    }

    fun updateCurrentDiaryPage(date: String) {
        viewModelScope.launch {
            _diaryUiState.value = diaryUiState.value.copy(
                currentDiaryCalendarPage = date
            )
        }
    }
}
