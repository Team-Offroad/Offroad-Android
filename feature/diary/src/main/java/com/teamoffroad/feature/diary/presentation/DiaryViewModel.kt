package com.teamoffroad.feature.diary.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.diary.domain.usecase.GetDiaryTutorialCheckedUseCase
import com.teamoffroad.feature.diary.domain.usecase.PatchDiaryTutorialCheckedUseCase
import com.teamoffroad.feature.diary.presentation.model.DiaryHintDialogState
import com.teamoffroad.feature.diary.presentation.model.DiarySideEffect
import com.teamoffroad.feature.diary.presentation.model.DiaryUiState
import com.teamoffroad.feature.diary.presentation.model.MemoryLight
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

    fun updateTimeSettingDialogState(state: Boolean) {
        viewModelScope.launch {
            _diaryUiState.value = diaryUiState.value.copy(
                timeSettingDialogVisibility = state
            )
        }
    }

    private fun updateMemoryLightInfo() {
        val dummyMemoryList = listOf(
            MemoryLight(
                id = 1,
                dailyRecommend = "내일 묵은지 돼지갈비 왕목살 세트 어때요?",
                content = "그리고 오늘의 기억을 오늘 하루동안 나눈 대화, 방문한 장소, " +
                        "시간 데이터를 바탕으로 요약합니다. 이때 단순 요약이 아니라 AI가 남기는" +
                        " 일종의 메시지 형태라고 보시면 될 것 같고 앞으로에 대한 기대, 응원," +
                        " 위로 등의 내용이 담겨 있습니다. 앞으로에 대한 기대, 응원, 위로 등의" +
                        " 내용이 담겨 있습니다. 내용이 담겨 있습니다. 앞으로에 대한 기대, 응원," +
                        " 위로 등의 내용이 담겨 있습니다.",
                summation = "오늘의 기억을 AI가 한 줄로 요약합니다.",
                year = 2020,
                month = 11,
                day = 12,
                hexCode = "asd"
            ),
            MemoryLight(
                id = 2,
                dailyRecommend = "내일 돈까스 어때요?",
                content = "안녕하세요",
                summation = "오늘의 기억을 AI가 한 줄로 요약합니다.",
                year = 2024,
                month = 9,
                day = 22,
                hexCode = "asd"
            )
        )
        viewModelScope.launch {
            _diaryUiState.value = diaryUiState.value.copy(
                memoryLightList = dummyMemoryList
            )
        }
    }

    fun updateMemoryLightState(date: String?) {
        updateMemoryLightInfo()
        viewModelScope.launch {
            _diaryUiState.value = diaryUiState.value.copy(
                memoryLigthVisibility = date
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
