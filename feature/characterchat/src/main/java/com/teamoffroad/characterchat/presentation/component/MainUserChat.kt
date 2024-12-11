package com.teamoffroad.characterchat.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.characterchat.presentation.model.CharacterChattingUiState
import com.teamoffroad.characterchat.presentation.model.UserChattingUiState
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.core.designsystem.theme.Sub55
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.offroad.feature.characterchat.R

@Composable
fun UserChat(
    characterChatUiState: State<CharacterChattingUiState>,
    chattingText: State<String>,
    userChatUiState: State<UserChattingUiState>,
    updateUserWatchingCharacterChat: (Boolean) -> Unit,
    updateUserChattingText: (String) -> Unit,
    updateShowUserChatTextField: (Boolean) -> Unit,
    sendChat: () -> Unit,
    updateCharacterChatExist: (Boolean) -> Unit,
) {
    if (userChatUiState.value.showUserChatTextField) {
        Column {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    FinishChatting(
                        updateShowUserChatTextField = updateShowUserChatTextField,
                        updateUserWatchingCharacterChat = updateUserWatchingCharacterChat,
                        characterChatUiState = characterChatUiState,
                        updateCharacterChatExist = updateCharacterChatExist,
                    )
                }
            }

            MainUserChatTextField(
                text = chattingText.value,
                userChatUiState = userChatUiState,
                updateShowUserChatTextField = updateShowUserChatTextField,
                keyboard = true,
                onValueChange = { text ->
                    updateUserChattingText(text)
                },
                onSendClick = {
                    updateUserWatchingCharacterChat(true)
                    sendChat() // 서버에 보내기
                    updateUserChattingText("") // 초기화
                },
                characterChatUiState = characterChatUiState
            )
        }
    }

}

@Composable
fun FinishChatting(
    backgroundColor: Color = Sub55,
    borderColor: Color = Sub,
    characterChatUiState: State<CharacterChattingUiState>,
    updateShowUserChatTextField: (Boolean) -> Unit,
    updateUserWatchingCharacterChat: (Boolean) -> Unit,
    updateCharacterChatExist: (Boolean) -> Unit,
) {
    Text(
        style = OffroadTheme.typography.subtitle2Semibold,
        text = stringResource(id = R.string.home_chat_finish),
        modifier = Modifier
            .padding(bottom = 8.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(20.dp)
            )
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(20.dp),
                color = borderColor
            )
            .padding(horizontal = 16.dp)
            .padding(vertical = 8.dp)
            .clickableWithoutRipple {
                if (!characterChatUiState.value.isCharacterChattingLoading) {
                    updateUserWatchingCharacterChat(false)
                    updateShowUserChatTextField(false)
                }
            },
        color = White
    )
}

@Composable
fun showUserChat(
    userChatUiState: State<UserChattingUiState>,
    characterChatUiState: State<CharacterChattingUiState>,
    userChattingText: State<String>,
    updateCharacterChatExist: (Boolean) -> Unit,
    updateUserWatchingCharacterChat: (Boolean) -> Unit,
    updateUserChattingText: (String) -> Unit,
    updateShowUserChatTextField: (Boolean) -> Unit,
    sendChat: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 370.dp) // TODO: TextField 위치 조정 필요
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            if (userChatUiState.value.showUserChatTextField) {
                UserChat(
                    characterChatUiState = characterChatUiState,
                    chattingText = userChattingText,
                    updateShowUserChatTextField = updateShowUserChatTextField,
                    updateUserWatchingCharacterChat = updateUserWatchingCharacterChat,
                    userChatUiState = userChatUiState,
                    sendChat = sendChat,
                    updateUserChattingText = updateUserChattingText,
                    updateCharacterChatExist = updateCharacterChatExist
                )
            }
        }
    }
}