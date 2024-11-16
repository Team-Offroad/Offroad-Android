package com.teamoffroad.characterchat.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.characterchat.domain.repository.CharacterChatRepository
import com.teamoffroad.characterchat.presentation.mapper.toUi
import com.teamoffroad.characterchat.presentation.model.CharacterChatUiState
import com.teamoffroad.characterchat.presentation.model.ChatModel
import com.teamoffroad.characterchat.presentation.model.ChatType.USER
import com.teamoffroad.characterchat.presentation.model.TimeType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class CharacterChatViewModel @Inject constructor(
    private val characterChatRepository: CharacterChatRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<CharacterChatUiState> = MutableStateFlow(CharacterChatUiState())
    val uiState: StateFlow<CharacterChatUiState> = _uiState.asStateFlow()

    private val _isChatting: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isChatting: StateFlow<Boolean> = _isChatting.asStateFlow()

    private val _chattingText: MutableStateFlow<String> = MutableStateFlow("")
    val chattingText: StateFlow<String> = _chattingText.asStateFlow()

    private val _characterId: MutableStateFlow<Int> = MutableStateFlow(-1)
    private val characterId: StateFlow<Int> = _characterId.asStateFlow()

    fun initCharacterId(characterId: Int) {
        _characterId.value = characterId
    }

    fun updateIsChatting(boolean: Boolean) {
        _isChatting.value = boolean
        _chattingText.value = ""
    }

    fun updateChattingText(text: String) {
        _chattingText.value = text
    }

    fun getChats() {
        viewModelScope.launch {
            runCatching {
                _uiState.value = uiState.value.copy(isLoading = true)
                characterChatRepository.fetchChats(characterId.value)
            }.onSuccess { chats ->
                _uiState.value = uiState.value.copy(
                    chats = chats.map { it.toUi() }.groupBy { it.date },
                    isLoading = false,
                )
            }.onFailure {
                _uiState.value = uiState.value.copy(isLoading = false, isError = true)
            }
        }
    }

    fun sendChat() {
        val chattingText = chattingText.value

        viewModelScope.launch {
            runCatching {
                val now = LocalDateTime.now()
                val userChat = ChatModel(
                    chatType = USER,
                    text = chattingText,
                    date = now.toLocalDate(),
                    time = Triple(TimeType.toTimeType(now.hour), now.hour, now.minute),
                )
                extendChat(userChat)
                _uiState.value = uiState.value.copy(isLoading = true)
                characterChatRepository.saveChat(characterId.value, chattingText)
            }.onSuccess { chat ->
                extendChat(chat.toUi())
                _uiState.value = uiState.value.copy(isLoading = false)
            }.onFailure {
                removeLastChat()
                _uiState.value = uiState.value.copy(isLoading = false)
            }
        }
    }

    private fun extendChat(chat: ChatModel) {
        _uiState.value = uiState.value.copy(
            chats = uiState.value.chats.toMutableMap().apply {
                put(chat.date, (get(chat.date) ?: emptyList()) + chat)
            }
        )
    }

    private fun removeLastChat() {
        _uiState.value = uiState.value.copy(
            chats = uiState.value.chats.toMutableMap().apply {
                val lastChatDate = keys.last()
                val lastChatList = get(lastChatDate)?.toMutableList() ?: mutableListOf()
                lastChatList.removeLast()
                put(lastChatDate, lastChatList)
            }
        )
    }
}
