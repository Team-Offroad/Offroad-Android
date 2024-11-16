package com.teamoffroad.characterchat.presentation

import androidx.lifecycle.ViewModel
import com.teamoffroad.characterchat.presentation.model.CharacterChatUiState
import com.teamoffroad.characterchat.presentation.model.ChatModel
import com.teamoffroad.characterchat.presentation.model.ChatType.ORB_CHARACTER
import com.teamoffroad.characterchat.presentation.model.ChatType.USER
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CharacterChatViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState: MutableStateFlow<CharacterChatUiState> = MutableStateFlow(CharacterChatUiState())
    val uiState: StateFlow<CharacterChatUiState> = _uiState.asStateFlow()

    private val _isChatting: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isChatting: StateFlow<Boolean> = _isChatting.asStateFlow()

    private val _chattingText: MutableStateFlow<String> = MutableStateFlow("")
    val chattingText: StateFlow<String> = _chattingText.asStateFlow()

    fun testInit() {
        val today = LocalDate.now()

        val dummyChats = mapOf(
            today.minusDays(4) to listOf(
                ChatModel(chatType = USER, text = "오브, 4일 전에 날씨 어땠나요?", time = "${today.minusDays(4)}T10:00:00Z"),
                ChatModel(chatType = ORB_CHARACTER, text = "그날은 비가 왔었어요.", time = "${today.minusDays(4)}T10:01:00Z"),
                ChatModel(chatType = USER, text = "오브, 비 오는 날 좋아하나요?", time = "${today.minusDays(4)}T10:05:00Z"),
                ChatModel(chatType = ORB_CHARACTER, text = "저는 비 오는 날의 소리를 좋아합니다.", time = "${today.minusDays(4)}T10:06:00Z")
            ),
            today.minusDays(3) to listOf(
                ChatModel(chatType = USER, text = "어제는 뭐 했나요?", time = "${today.minusDays(3)}T15:30:00Z"),
                ChatModel(chatType = ORB_CHARACTER, text = "어제는 독서를 했답니다.", time = "${today.minusDays(3)}T15:35:00Z"),
                ChatModel(chatType = USER, text = "어떤 책을 읽었나요?", time = "${today.minusDays(3)}T15:40:00Z"),
                ChatModel(chatType = ORB_CHARACTER, text = "AI와 철학에 관한 책이었어요.", time = "${today.minusDays(3)}T15:42:00Z")
            ),
            today.minusDays(2) to listOf(
                ChatModel(chatType = USER, text = "오브, 좋아하는 음식이 뭐예요?", time = "${today.minusDays(2)}T08:00:00Z"),
                ChatModel(chatType = ORB_CHARACTER, text = "저는 디지털 피자요!", time = "${today.minusDays(2)}T08:01:00Z"),
                ChatModel(chatType = USER, text = "피자는 어떤 토핑이 좋나요?", time = "${today.minusDays(2)}T08:10:00Z"),
                ChatModel(chatType = ORB_CHARACTER, text = "가상 올리브와 데이터 소스 토핑이요!", time = "${today.minusDays(2)}T08:11:00Z")
            ),
            today.minusDays(1) to listOf(
                ChatModel(chatType = USER, text = "오늘 기분은 어떤가요?", time = "${today.minusDays(1)}T20:20:00Z"),
                ChatModel(chatType = ORB_CHARACTER, text = "오늘은 아주 기분이 좋습니다!", time = "${today.minusDays(1)}T20:21:00Z"),
                ChatModel(chatType = USER, text = "왜 기분이 좋으신가요?", time = "${today.minusDays(1)}T20:25:00Z"),
                ChatModel(chatType = ORB_CHARACTER, text = "좋은 질문이었기 때문이죠!", time = "${today.minusDays(1)}T20:26:00Z")
            ),
            today to listOf(
                ChatModel(chatType = USER, text = "안녕하세요, 오브!", time = "${today}T09:00:00Z"),
                ChatModel(chatType = ORB_CHARACTER, text = "안녕하세요! 오늘 하루도 화이팅!", time = "${today}T09:01:00Z"),
                ChatModel(chatType = USER, text = "오늘 날씨는 어떤가요?", time = "${today}T09:10:00Z"),
                ChatModel(chatType = ORB_CHARACTER, text = "맑고 화창합니다. 멋진 하루 되세요!", time = "${today}T09:11:00Z")
            )
        )
        _uiState.value = CharacterChatUiState(
            chats = dummyChats,
            characterName = "오브",
            isLoading = false,
        )
    }

    fun updateIsChatting(boolean: Boolean) {
        _isChatting.value = boolean
        _chattingText.value = ""
    }

    fun updateChattingText(text: String) {
        _chattingText.value = text
    }

    fun sendChat() {
        // TODO: Implement sendChat
    }
}
