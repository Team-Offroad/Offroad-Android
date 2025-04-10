package com.teamoffroad.feature.recommendplace.presentation.component

import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.characterchat.presentation.CharacterChatViewModel
import com.teamoffroad.characterchat.presentation.component.CharacterChatHeader
import com.teamoffroad.characterchat.presentation.component.CharacterChats
import com.teamoffroad.characterchat.presentation.component.ChatTextField
import com.teamoffroad.core.designsystem.component.FullLinearLoadingAnimation
import com.teamoffroad.core.designsystem.component.actionBarPadding
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub4
import com.teamoffroad.offroad.feature.recommendplace.R

@Composable
fun RecommendPlaceChat(
    name: String,
    text: String,
    time: String,
    onClose: () -> Unit
) {
    val characterChatViewModel: CharacterChatViewModel = hiltViewModel()
    val uiState = characterChatViewModel.uiState.collectAsStateWithLifecycle()
    val isChatting = characterChatViewModel.isChatting.collectAsStateWithLifecycle()
    val chattingText = characterChatViewModel.chattingText.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    val contextView = LocalView.current
    val keyboardHeight = remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        characterChatViewModel.updateIsChatting(true)
    }

    DisposableEffect(contextView) {
        val rect = Rect()
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            contextView.getWindowVisibleDisplayFrame(rect)
            val screenHeight = contextView.rootView.height
            val keypadHeight = screenHeight - rect.bottom
            keyboardHeight.intValue = if (keypadHeight > screenHeight * 0.15) keypadHeight else 0
        }
        contextView.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            contextView.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }

    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
            .background(brush = Brush.horizontalGradient(RecommendPlaceFillGradientColors))
    ) {
        Column(
            modifier = Modifier
                .navigationPadding()
                .actionBarPadding()
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_recommend_place_close),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.End)
                    .clickableWithoutRipple { onClose() }
            )

            Row(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "오브",
                    style = OffroadTheme.typography.textBold,
                    color = Sub4,
                )
                Text(
                    text = ": ",
                    color = Main2,
                    style = OffroadTheme.typography.textRegular,
                    modifier = Modifier.padding(start = 4.dp),
                )
                Text(
                    text = "오브의 추천소에 어서와~ 여기서는 ~~~소개소개 ",
                    style = OffroadTheme.typography.textRegular,
                    color = Main2,
                )
            }

            CharacterChats(
                modifier = Modifier
                    .weight(1f),
                characterName = uiState.value.characterName,
                arrangedChats = uiState.value.chats,
                bottomPadding = when (isChatting.value) {
                    true -> LocalDensity.current.run {
                        keyboardHeight.intValue
                    }

                    false -> 0
                },
                isChatting = isChatting.value,
                isSending = uiState.value.isSending,
                isLoadable = uiState.value.isLoadable,
                updateChats = characterChatViewModel::handleChatState,
                updateIsChatting = characterChatViewModel::updateIsChatting,
                navigateToRecommendPlace = { }
            )

            ChatTextField(
                modifier = Modifier
                    .padding(bottom = LocalDensity.current.run { keyboardHeight.intValue.toDp() }),
//                    .align(Alignment.BottomCenter),
                // 여기 밑은 나중에 수정하기
                text = chattingText.value,
                isChatting = isChatting.value,
                onValueChange = { text ->
                    characterChatViewModel.updateChattingText(text)
                },
                onFocusChange = { isFocused ->
                    characterChatViewModel.updateIsChatting(isFocused)
                },
                onSendClick = {
                    characterChatViewModel.performChat()
                    characterChatViewModel.updateIsChatting(false)
                },
            )
        }

        FullLinearLoadingAnimation(isLoading = uiState.value.isLoading && uiState.value.chats.values.isEmpty())
    }
}