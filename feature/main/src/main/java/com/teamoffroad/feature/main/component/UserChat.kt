package com.teamoffroad.feature.main.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.core.designsystem.theme.Sub55
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.main.UserChattingUiState
import com.teamoffroad.offroad.feature.home.R

@Composable
fun UserChat(
//    isChatting: MutableState<Boolean>,
    chattingText: State<String>,
    userChatUiState: State<UserChattingUiState>,
    updateUserWatchingCharacterChat: (Boolean) -> Unit,
//    sendMessage: MutableState<String>,
//    userSendChat: MutableState<Boolean>,
//    updateCharacterChatting: (Boolean) -> Unit,
    updateUserChattingText: (String) -> Unit,
    updateShowUserChatTextField: (Boolean) -> Unit,
    sendChat: () -> Unit,
) {
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
                FinishChatting()
            }
        }

        HomeUserChatTextField(
            text = chattingText.value,
            userChatUiState = userChatUiState,
            updateShowUserChatTextField = updateShowUserChatTextField,
            keyboard = true,
            onValueChange = { text ->
                updateUserChattingText(text)
            },
            onSendClick = {
                // TODO: 캐릭터 챗이 계속 보이도록
                updateUserWatchingCharacterChat(true)
                sendChat() // 서버에 보내기
                updateUserChattingText("") // 초기화
            }
        )
    }
}

@Composable
fun FinishChatting(
    //isChatting: MutableState<Boolean>,
    backgroundColor: Color = Sub55,
    borderColor: Color = Sub
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
                //isChatting.value = false
            },
        color = White
    )
}