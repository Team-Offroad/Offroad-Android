package com.teamoffroad.characterchat.presentation

import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.characterchat.presentation.component.CharacterChatHeader
import com.teamoffroad.characterchat.presentation.component.CharacterChats
import com.teamoffroad.characterchat.presentation.component.ChatTextField
import com.teamoffroad.core.designsystem.component.FullLinearLoadingAnimation
import com.teamoffroad.core.designsystem.component.actionBarPadding
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.offroad.feature.characterchat.R

@Composable
fun CharacterChatScreen(
    characterId: Int?,
    characterName: String,
    navigateToBack: () -> Unit,
    characterChatViewModel: CharacterChatViewModel = hiltViewModel(),
) {
    val uiState = characterChatViewModel.uiState.collectAsStateWithLifecycle()
    val isChatting = characterChatViewModel.isChatting.collectAsStateWithLifecycle()
    val chattingText = characterChatViewModel.chattingText.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    val contextView = LocalView.current
    val keyboardHeight = remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        characterChatViewModel.initCharacterId(characterId, characterName)
        characterChatViewModel.handleChatState()
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
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        contentAlignment = Alignment.BottomCenter,
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_character_chat),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        Column(
            modifier = Modifier
                .navigationPadding()
                .actionBarPadding()
                .fillMaxSize(),
        ) {
            CharacterChatHeader(
                modifier = Modifier,
                characterName = uiState.value.characterName,
                navigateToBack = navigateToBack,
            )
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
            )
        }
        ChatTextField(
            modifier = Modifier
                .padding(bottom = LocalDensity.current.run { keyboardHeight.intValue.toDp() })
                .align(Alignment.BottomCenter),
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
