package com.teamoffroad.feature.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.characterchat.domain.model.Chat
import com.teamoffroad.characterchat.domain.repository.CharacterChatRepository
import com.teamoffroad.characterchat.presentation.model.ChatModel
import com.teamoffroad.characterchat.presentation.model.ChatType
import com.teamoffroad.characterchat.presentation.model.TimeType
import com.teamoffroad.core.common.domain.model.NotificationEvent
import com.teamoffroad.core.common.domain.repository.DeviceTokenRepository
import com.teamoffroad.core.common.domain.usecase.SetAutoSignInUseCase
import com.teamoffroad.feature.home.domain.model.Emblem
import com.teamoffroad.feature.home.domain.model.UserQuests
import com.teamoffroad.feature.home.domain.model.UsersAdventuresInformation
import com.teamoffroad.feature.home.domain.repository.UserRepository
import com.teamoffroad.feature.home.domain.usecase.PostFcmTokenUseCase
import com.teamoffroad.feature.home.presentation.component.UiState
import com.teamoffroad.feature.home.presentation.component.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val characterChatRepository: CharacterChatRepository,
    private val setAutoSignInUseCase: SetAutoSignInUseCase,
    private val deviceTokenRepository: DeviceTokenRepository,
    private val fcmTokenUseCase: PostFcmTokenUseCase,
) : ViewModel() {

    private val _getUsersAdventuresInformationState =
        MutableStateFlow<UiState<UsersAdventuresInformation>>(
            UiState.Loading
        )
    val getUsersAdventuresInformationState = _getUsersAdventuresInformationState.asStateFlow()

    private val _selectedEmblem = MutableStateFlow("")
    val selectedEmblem = _selectedEmblem.asStateFlow()

    private val _baseCharacterImage = MutableStateFlow("")
    val baseCharacterImage = _baseCharacterImage.asStateFlow()

    private val _motionCharacterUrl: MutableStateFlow<String?> = MutableStateFlow(null)
    val motionCharacterUrl = _motionCharacterUrl.asStateFlow()

    private val _category = MutableStateFlow("NONE")
    val category = _category.asStateFlow()

    private val _getEmblemsState = MutableStateFlow<UiState<List<Emblem>>>(UiState.Loading)
    val getEmblemsState = _getEmblemsState.asStateFlow()

    private val _patchEmblemState = MutableStateFlow<UiState<Unit>>(UiState.Loading)
    val patchEmblemState = _patchEmblemState.asStateFlow()

    private val _getUserQuestsState = MutableStateFlow<UiState<UserQuests>>(UiState.Loading)
    val getUserQuestsState = _getUserQuestsState.asStateFlow()

    private val _sendChatState = MutableStateFlow<UiState<Chat>>(UiState.Loading)
    val sendChatState = _sendChatState.asStateFlow()

    private val _circleProgressBar = MutableStateFlow(0f)
    val circleProgressBar = _circleProgressBar.asStateFlow()

    private val _linearProgressBar = MutableStateFlow(0f)
    val linearProgressBar = _linearProgressBar.asStateFlow()

    private val _isChatting: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isChatting: StateFlow<Boolean> = _isChatting.asStateFlow()

    private val _chattingText: MutableStateFlow<String> = MutableStateFlow("")
    val chattingText: StateFlow<String> = _chattingText.asStateFlow()

    var asd = MutableStateFlow("")
    init {
        //아까 CharacterChatBroadcastReceiver에서 게시한 브로드캐스트리시버를 여기서 받습니다.
        EventBus.getDefault().register(this)
    }

    //뷰모델이 삭제될때 이벤트버스도 해제시켜줍니다.
    override fun onCleared() {
        super.onCleared()
        EventBus.getDefault().unregister(this)
    }

    //브로드캐스트리시버가 작동할때마다 동작하는 함수(fcm발송 > 앱이 포그라운드에 있고, 타입이 캐릭터채팅이라면 작동)
    //그런데 홈화면이 아니고 다른화면에서 이 함수가 호출되면 ui가 활성되있지 않기 때문에 ui작업을 할 수 없습니다.(함수 실행될때 로그는 찍힘)
    //그래서 데이터스토어 같은 로컬저장소에 데이터와 캐릭터 채팅확인 여부를 저장해두었다가
    //홈화면에 들어와서 채팅확인 여부가 x라면 알림을 보여주고, 알림을 봤다면 다시 채팅확인 여부가 o로 만드는식으로 하면 될거같습니다.
    //그래서 포스트맨으로 fcm쏴보면서 요함수에서 하면 될 것 같습니다.
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onNotificationEvent(event: NotificationEvent) {
        Log.d("characterChat data", event.toString())
    }

    fun getUsersAdventuresInformation(category: String) {
        viewModelScope.launch {
            runCatching {
                userRepository.getUsersAdventuresInformation(category)
            }.onSuccess { state ->
                _getUsersAdventuresInformationState.emit(UiState.Success(state))
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

    fun updateCategory(category: String) {
        _category.value = category
    }

    fun updateCircleProgressBar(amount: Float, total: Float) {
        _circleProgressBar.value = if (total == 0f) 0f else amount / total
    }

    fun updateLinearProgressBar(amount: Float, total: Float) {
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

    fun updateChattingText(text: String) {
        _chattingText.value = text
    }

    fun updateIsChatting(boolean: Boolean) {
        _isChatting.value = boolean
        _chattingText.value = ""
    }

    fun sendChat() {
        val chattingText = chattingText.value

        viewModelScope.launch {
            runCatching {
                val now = LocalDateTime.now()
                val userChat = ChatModel(
                    chatType = ChatType.USER,
                    text = chattingText,
                    date = now.toLocalDate(),
                    time = Triple(TimeType.toTimeType(now.hour), now.hour, now.minute),
                )
                characterChatRepository.saveChat(1, chattingText)
            }.onSuccess { chat ->
                // _patchEmblemState.emit(UiState.Success(state))
                // 보낸 채팅 내용 홈에 보여주어야 함
                _sendChatState.emit(UiState.Success(chat))
            }.onFailure { t ->
                val errorMessage = getErrorMessage(t)
                _sendChatState.emit(UiState.Failure(errorMessage))
            }
        }
    }

    fun updateFcmToken() {
        viewModelScope.launch {
            val deviceToken = deviceTokenRepository.deviceToken.first()
            if (deviceToken.isBlank()) return@launch
            runCatching {
                fcmTokenUseCase.invoke(deviceToken)
            }.onSuccess { }
                .onFailure {}
        }
    }
}