package com.teamoffroad.characterchat.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.characterchat.presentation.component.CharacterChatHeader
import com.teamoffroad.characterchat.presentation.component.CharacterChats
import com.teamoffroad.characterchat.presentation.component.ChatButton
import com.teamoffroad.characterchat.presentation.component.ChatTextField
import com.teamoffroad.characterchat.presentation.component.DEFAULT_IME_PADDING
import com.teamoffroad.characterchat.presentation.component.rememberKeyboardHeight
import com.teamoffroad.core.designsystem.component.actionBarPadding
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.offroad.feature.characterchat.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CharacterChatScreen(
    characterId: Int,
    navigateToBack: () -> Unit,
    characterChatViewModel: CharacterChatViewModel = hiltViewModel(),
) {
    val uiState = characterChatViewModel.uiState.collectAsStateWithLifecycle()
    val isChatting = characterChatViewModel.isChatting.collectAsStateWithLifecycle()
    val chattingText = characterChatViewModel.chattingText.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    val imeHeight = rememberKeyboardHeight()
    val keyboardOffset = if (imeHeight == DEFAULT_IME_PADDING) 0 else (imeHeight - DEFAULT_IME_PADDING)
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        characterChatViewModel.initCharacterId(characterId)
        characterChatViewModel.getChats()
    }

    LaunchedEffect(uiState.value.chats) {
        coroutineScope.launch {
            listState.animateScrollToItem(listState.layoutInfo.totalItemsCount - 1)
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
                modifier = Modifier
                    .padding(top = keyboardOffset.dp),
                characterName = uiState.value.characterName,
                navigateToBack = navigateToBack,
            )
            CharacterChats(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = keyboardOffset.dp),
                characterName = uiState.value.characterName,
                arrangedChats = uiState.value.chats,
                bottomPadding = keyboardOffset,
                listState = listState,
            )
        }
        ChatTextField(
            modifier = Modifier
                .padding(bottom = 300.dp)
                .imePadding()
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
                characterChatViewModel.sendChat()
                characterChatViewModel.updateIsChatting(false)
            },
        )
        ChatButton(
            modifier = Modifier
                .padding(bottom = 198.dp)
                .align(Alignment.BottomCenter),
            isChatting = isChatting.value,
            onClick = {
                characterChatViewModel.updateIsChatting(true)
                coroutineScope.launch {
                    delay(400)
                    listState.animateScrollToItem(listState.layoutInfo.totalItemsCount - 1)
                }
            },
        )
    }
}
