package com.teamoffroad.feature.home.presentation.component.character

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun CharacterChatAnimation(
    isCharacterChatting: State<Boolean>,
    isChatting: MutableState<Boolean>,
    isCharacterChattingLoading: State<Boolean>,
    answerCharacterChat: MutableState<Boolean>,
    characterName: String,
    characterContent: String,
    navigateToCharacterChatScreen: (Int, String) -> Unit
) {
    val offsetY = remember { Animatable(-10.dp.value) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(isCharacterChatting) {
        coroutineScope.launch {
            offsetY.animateTo(
                targetValue = 0.dp.value,
                animationSpec = tween(durationMillis = 500)
            )
        }
    }

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .offset(y = offsetY.value.dp)
            .padding(start = 24.dp, top = 70.dp, end = 24.dp)
    ) {
        CharacterChat(
            isChatting = isChatting,
            isCharacterChattingLoading = isCharacterChattingLoading,
            answerCharacterChat = answerCharacterChat,
            characterName = characterName,
            characterContent = characterContent,
            navigateToCharacterChatScreen = navigateToCharacterChatScreen
        )
    }
}