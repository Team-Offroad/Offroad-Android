package com.teamoffroad.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.characterchat.domain.repository.CharacterChatRepository
import com.teamoffroad.core.common.domain.model.DiaryCreateNotificationEvent
import com.teamoffroad.core.common.domain.model.PlaceCategory
import com.teamoffroad.core.common.domain.repository.TokenRepository
import com.teamoffroad.core.common.domain.usecase.GetCompleteQuestListUseCase
import com.teamoffroad.core.common.domain.usecase.GetRecentVisitedCategoryUseCase
import com.teamoffroad.core.common.domain.usecase.SetAutoSignInUseCase
import com.teamoffroad.feature.diary.domain.usecase.GetDiaryCheckLatestUseCase
import com.teamoffroad.feature.home.domain.model.Emblem
import com.teamoffroad.feature.home.domain.model.UserQuests
import com.teamoffroad.feature.home.domain.model.UsersAdventuresInformation
import com.teamoffroad.feature.home.domain.repository.UserRepository
import com.teamoffroad.feature.home.domain.usecase.PostDiarySettingUseCase
import com.teamoffroad.feature.home.domain.usecase.PostFcmTokenUseCase
import com.teamoffroad.feature.home.presentation.component.UiState
import com.teamoffroad.feature.home.presentation.component.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val userRepository: UserRepository,
        private val setAutoSignInUseCase: SetAutoSignInUseCase,
        private val deviceTokenRepository: TokenRepository,
        private val fcmTokenUseCase: PostFcmTokenUseCase,
        private val characterChatRepository: CharacterChatRepository,
        private val diarySettingUseCase: PostDiarySettingUseCase,
        private val getDiaryCheckLatestUseCase: GetDiaryCheckLatestUseCase,
        private val getCompleteQuestListUseCase: GetCompleteQuestListUseCase,
        private val getRecentVisitedCategoryUseCase: GetRecentVisitedCategoryUseCase,
    ) : ViewModel() {
        private val _getUsersAdventuresInformationState =
            MutableStateFlow<UiState<UsersAdventuresInformation>>(
                UiState.Loading,
            )
        val getUsersAdventuresInformationState = _getUsersAdventuresInformationState.asStateFlow()

        private val _selectedEmblem = MutableStateFlow("")
        val selectedEmblem = _selectedEmblem.asStateFlow()

        private val _baseCharacterImage = MutableStateFlow("")
        val baseCharacterImage = _baseCharacterImage.asStateFlow()

        private val _motionCharacterUrl: MutableStateFlow<String?> = MutableStateFlow(null)
        val motionCharacterUrl = _motionCharacterUrl.asStateFlow()

        private val _category = MutableStateFlow(PlaceCategory.NONE)
        val category = _category.asStateFlow()

        private val _getEmblemsState = MutableStateFlow<UiState<List<Emblem>>>(UiState.Loading)
        val getEmblemsState = _getEmblemsState.asStateFlow()

        private val _patchEmblemState = MutableStateFlow<UiState<Unit>>(UiState.Loading)
        val patchEmblemState = _patchEmblemState.asStateFlow()

        private val _getUserQuestsState = MutableStateFlow<UiState<UserQuests>>(UiState.Loading)
        val getUserQuestsState = _getUserQuestsState.asStateFlow()

        private val _circleProgressBar = MutableStateFlow(0f)
        val circleProgressBar = _circleProgressBar.asStateFlow()

        private val _linearProgressBar = MutableStateFlow(0f)
        val linearProgressBar = _linearProgressBar.asStateFlow()

        private val _characterName = MutableStateFlow("")
        val characterName = _characterName.asStateFlow()

        private val _newDiaryExist = MutableStateFlow(true)
        val newDiaryExist = _newDiaryExist.asStateFlow()

        private val _diaryCreateState = MutableStateFlow(false)
        val diaryCreateState = _diaryCreateState.asStateFlow()

        private val _completeQuests = MutableStateFlow<List<String>>(emptyList())
        val completeQuests = _completeQuests.asStateFlow()

        init {
            EventBus.getDefault().register(this)
        }

        override fun onCleared() {
            super.onCleared()
            EventBus.getDefault().unregister(this)
        }

        @Subscribe(threadMode = ThreadMode.MAIN)
        fun onNotificationEvent(event: DiaryCreateNotificationEvent) {
            viewModelScope.launch {
                getDiaryCheckLatest()
                _diaryCreateState.emit(event.state)
            }
        }

        private fun getUsersAdventuresInformation(category: PlaceCategory) {
            viewModelScope.launch {
                runCatching {
                    userRepository.getUsersAdventuresInformation(category.name)
                }.onSuccess { state ->
                    _getUsersAdventuresInformationState.emit(UiState.Success(state))
                    _characterName.value = state.characterName
                    updateSelectedEmblem(state.emblemName)
                    updateCharacterImage(state.baseImageUrl)
                    updateMotionImageUrl(state.motionImageUrl)
                }.onFailure { t ->
                    val errorMessage = getErrorMessage(t)
                    _getUsersAdventuresInformationState.emit(UiState.Failure(errorMessage))
                }
            }
        }

        private fun updateSelectedEmblem(emblemName: String) {
            _selectedEmblem.value = emblemName
        }

        private fun updateCharacterImage(imageUrl: String) {
            _baseCharacterImage.value = imageUrl
        }

        private fun updateMotionImageUrl(motionImageUrl: String?) {
            _motionCharacterUrl.value = motionImageUrl
        }

        fun updateCategory() {
            viewModelScope.launch {
                runCatching {
                    getRecentVisitedCategoryUseCase()
                }.onSuccess {
                    _category.value = it
                    if (it != PlaceCategory.NONE) getUsersAdventuresInformation(it)
                }
            }
        }

        fun updateCircleProgressBar(
            amount: Float,
            total: Float,
        ) {
            _circleProgressBar.value = if (total == 0f) 0f else amount / total
        }

        fun updateLinearProgressBar(
            amount: Float,
            total: Float,
        ) {
            _linearProgressBar.value = if (total == 0f) 0f else amount / total
        }

        fun getEmblems() {
            viewModelScope.launch {
                runCatching {
                    userRepository.getEmblems()
                }.onSuccess { state ->
                    _getEmblemsState.emit(UiState.Success(state))
                }.onFailure { t ->
                    val errorMessage = getErrorMessage(t)
                    _getEmblemsState.emit(UiState.Failure(errorMessage))
                }
            }
        }

        fun patchEmblem(emblem: Emblem) {
            viewModelScope.launch {
                runCatching {
                    userRepository.patchEmblem(emblem.emblemCode)
                }.onSuccess { state ->
                    _patchEmblemState.emit(UiState.Success(state))
                    updateSelectedEmblem(emblem.emblemName)
                }.onFailure { t ->
                    val errorMessage = getErrorMessage(t)
                    _patchEmblemState.emit(UiState.Failure(errorMessage))
                }
            }
        }

        fun getUserQuests() {
            viewModelScope.launch {
                runCatching {
                    userRepository.getUserQuests()
                }.onSuccess { state ->
                    _getUserQuestsState.emit(UiState.Success(state))
                }.onFailure { t ->
                    val errorMessage = getErrorMessage(t)
                    _getUserQuestsState.emit(UiState.Failure(errorMessage))
                }
            }
        }

        fun updateAutoSignIn() {
            viewModelScope.launch {
                setAutoSignInUseCase.invoke(true)
            }
        }

        fun updateFcmToken() {
            viewModelScope.launch {
                val deviceToken = deviceTokenRepository.getDeviceToken().first()
                if (deviceToken.isBlank()) return@launch
                runCatching {
                    fcmTokenUseCase.invoke(deviceToken)
                }.onSuccess { }
                    .onFailure {}
            }
        }

        fun getDiaryCheckLatest() {
            viewModelScope.launch {
                getDiaryCheckLatestUseCase.invoke().onSuccess {
                    if (it != null) {
                        _newDiaryExist.emit(it)
                    }
                }
            }
        }

        fun initUserDiarySetting() {
            viewModelScope.launch { runCatching { diarySettingUseCase.invoke() } }
        }

        fun updateDiaryCreateDialogUnShown() {
            viewModelScope.launch {
                _diaryCreateState.emit(false)
            }
        }

        fun loadCompleteQuests() {
            viewModelScope.launch {
                runCatching {
                    getCompleteQuestListUseCase()
                }.onSuccess { quests ->
                    _completeQuests.value = quests
                }
            }
        }

        companion object {
            const val MIN_SHOWN_EMBLEM_DIALOG = 6
        }
    }
