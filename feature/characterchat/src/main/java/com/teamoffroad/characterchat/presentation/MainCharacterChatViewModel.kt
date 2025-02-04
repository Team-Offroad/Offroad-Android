package com.teamoffroad.characterchat.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.characterchat.domain.model.Chat
import com.teamoffroad.characterchat.domain.repository.CharacterChatRepository
import com.teamoffroad.characterchat.presentation.model.CharacterChatLastUnreadUiState
import com.teamoffroad.characterchat.presentation.model.CharacterChattingUiState
import com.teamoffroad.characterchat.presentation.model.UiState
import com.teamoffroad.characterchat.presentation.model.UserChattingUiState
import com.teamoffroad.core.common.domain.model.NotificationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

@HiltViewModel
class MainCharacterChatViewModel @Inject constructor(
    private val characterChatRepository: CharacterChatRepository,
) : ViewModel() {
    private val _characterChatUiState = MutableStateFlow(CharacterChattingUiState())
    val characterChatUiState = _characterChatUiState.asStateFlow()

    private val _userChatUiState = MutableStateFlow(UserChattingUiState())
    val userChatUiState = _userChatUiState.asStateFlow()

    private val _characterChatLastUnreadUiState = MutableStateFlow(CharacterChatLastUnreadUiState())
    val characterChatLastUnreadUiState = _characterChatLastUnreadUiState.asStateFlow()

    private val _userChattingText: MutableStateFlow<String> = MutableStateFlow("")
    val userChattingText: StateFlow<String> = _userChattingText.asStateFlow()

    private val _sendChatState = MutableStateFlow<UiState<Chat>>(UiState.Loading)
    val sendChatState = _sendChatState.asStateFlow()

    private val _characterName = MutableStateFlow("")
    val characterName = _characterName.asStateFlow()

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

        _characterChatUiState.value = _characterChatUiState.value.copy(
            isCharacterChattingLoading = true
        )

        val characterName = event.characterName
        val characterContent = event.characterContent
        if (characterName != null && characterContent != null) {
            _characterChatUiState.value = characterChatUiState.value.copy(
                characterName = characterName,
                characterChatContent = characterContent,
                isCharacterChattingExist = true,
                isAnswerButtonClicked = false
            )
        }
        _characterChatUiState.value = _characterChatUiState.value.copy(
            isCharacterChattingLoading = false
        )
    }

    fun getCharacterChatLastUnread() {
        _characterChatLastUnreadUiState.value = _characterChatLastUnreadUiState.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            runCatching {
                characterChatRepository.fetchChatsLastUnread()
            }.onSuccess { data ->
                _characterChatLastUnreadUiState.value = _characterChatLastUnreadUiState.value.copy(
                    doesAllRead = data.doesAllRead,
                    isLoading = false,
                )
                _characterChatUiState.value = _characterChatUiState.value.copy(
                    characterName = data.characterName ?: "",
                    characterChatContent = data.content ?: ""
                )
                // 답장하기 버튼 없애기
                _characterChatUiState.value = _characterChatUiState.value.copy(
                    isAnswerButtonClicked = true
                )
            }.onFailure { t ->
                _characterChatLastUnreadUiState.value = _characterChatLastUnreadUiState.value.copy(
                    isLoading = false,
                    errorMessage = t.message.toString()
                )
            }
        }
    }

    fun updateLastUnreadChatDosAllRead(state: Boolean) {
        _characterChatLastUnreadUiState.value = _characterChatLastUnreadUiState.value.copy(
            doesAllRead = state
        )
    }

    fun updateCharacterName(name: String) {
        _characterChatUiState.value = _characterChatUiState.value.copy(
            characterName = name
        )
        _characterName.value = name
    }

    fun updateAnswerCharacterChatButtonState(state: Boolean) {
        _characterChatUiState.value = _characterChatUiState.value.copy(
            isAnswerButtonClicked = state
        )
    }

    fun updateCharacterChatExist(state: Boolean) {
        _characterChatUiState.value = _characterChatUiState.value.copy(
            isCharacterChattingExist = state
        )
    }

    fun updateUserWatchingCharacterChat(state: Boolean) {
        _characterChatUiState.value = _characterChatUiState.value.copy(
            isUserWatchingCharacterChat = state
        )
    }

    fun updateUserChattingText(text: String) {
        _userChattingText.value = text
    }

    fun updateShowUserChatTextField(state: Boolean) {
        _userChatUiState.value = _userChatUiState.value.copy(
            showUserChatTextField = state
        )
    }

    fun sendChat() {
        _userChatUiState.value = _userChatUiState.value.copy(
            chatContent = userChattingText.value,
            showUserChatTextField = true
        )
        _characterChatUiState.value = _characterChatUiState.value.copy(
            isCharacterChattingLoading = true
        )

        viewModelScope.launch {
            runCatching {
                characterChatRepository.saveChat(1, _userChatUiState.value.chatContent)
            }.onSuccess { chat ->
                _sendChatState.emit(UiState.Success(chat))
                _userChatUiState.value = _userChatUiState.value.copy(
                    isUserChattingLoading = true,
                )

                val characterContent = chat.content
                _characterChatUiState.value = _characterChatUiState.value.copy(
                    characterChatContent = characterContent,
                    isCharacterChattingExist = true,
                    isAnswerButtonClicked = true,
                    isCharacterChattingLoading = false,
                    characterName = characterName.value
                )

            }.onFailure { t ->
                _sendChatState.emit(UiState.Failure(t.message.toString()))
            }
        }
    }
}
