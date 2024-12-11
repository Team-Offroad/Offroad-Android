package com.teamoffroad.characterchat.presentation.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.teamoffroad.characterchat.presentation.model.CharacterChattingUiState
import com.teamoffroad.characterchat.presentation.model.UserChattingUiState
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.BtnInactive
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub4
import com.teamoffroad.offroad.feature.characterchat.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CharacterChat(
    characterChatUiState: State<CharacterChattingUiState>,
    userChatUiState: State<UserChattingUiState>,
    characterTextColor: Color = Sub4,
    characterTextStyle: TextStyle = OffroadTheme.typography.textBold,
    messageTextColor: Color = Main2,
    messageTextStyle: TextStyle = OffroadTheme.typography.textRegular,
    backgroundColor: Color = Main3,
    borderColor: Color = BtnInactive,
    updateAnswerCharacterChatButtonState: (Boolean) -> Unit,
    updateUserWatchingCharacterChat: (Boolean) -> Unit,
    updateShowUserChatTextField: (Boolean) -> Unit,
    updateCharacterChatExist: (Boolean) -> Unit,
    navigateToCharacterChatScreen: (Int, String) -> Unit,
) {
    val isExpanded = remember { mutableStateOf(false) }
    val characterChatAccordionAngle by animateFloatAsState(
        targetValue = if (isExpanded.value) 180f else 0f,
        animationSpec = tween(durationMillis = 300), label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(12.dp),
                color = borderColor
            )
            .padding(vertical = 14.dp, horizontal = 18.dp)
            .clickableWithoutRipple {
                navigateToCharacterChatScreen(-1, characterChatUiState.value.characterName)
                updateCharacterChatExist(false)// 선톡 이제 안보이게
            }
    ) {
        Column {
            Row {
                Text(
                    text = "${characterChatUiState.value.characterName} : ",
                    modifier = Modifier,
                    color = characterTextColor,
                    style = characterTextStyle
                )

                if (characterChatUiState.value.isCharacterChattingLoading) {
                    Box(
                        modifier = Modifier
                            .size(width = 54.dp, height = 27.dp)
                    ) {
                        val composition by rememberLottieComposition(
                            LottieCompositionSpec.RawRes(
                                com.teamoffroad.offroad.core.designsystem.R.raw.loading_linear
                            )
                        )
                        val animationState = animateLottieCompositionAsState(
                            composition,
                            iterations = LottieConstants.IterateForever
                        )

                        if (animationState.isAtEnd && animationState.isPlaying) {
                            LaunchedEffect(Unit) { }
                        }

                        LottieAnimation(composition, animationState.progress)
                    }
                } else {
                    val chatLine = characterChatUiState.value.characterChatContent.length
                    Text(
                        text = characterChatUiState.value.characterChatContent,
                        modifier = Modifier.weight(1f),
                        color = messageTextColor,
                        style = messageTextStyle,
                        maxLines = if (!isExpanded.value && chatLine >= MAX_CHARACTER_CHAT_LINE) 2 else Int.MAX_VALUE,
                        overflow = TextOverflow.Ellipsis,
                    )

                    if (chatLine >= MAX_CHARACTER_CHAT_LINE) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_home_accordian),
                            contentDescription = "accordion down",
                            modifier = Modifier
                                .graphicsLayer(rotationX = characterChatAccordionAngle)
                                .clickableWithoutRipple {
                                    updateUserWatchingCharacterChat(true)
                                    isExpanded.value = !isExpanded.value
                                }
                        )
                    }

                }

            }

            if (!characterChatUiState.value.isAnswerButtonClicked && !userChatUiState.value.showUserChatTextField) {
                AnswerCharacterChat(
                    characterChatUiState = characterChatUiState,
                    updateAnswerCharacterChatButtonState = updateAnswerCharacterChatButtonState,
                    updateUserWatchingCharacterChat = updateUserWatchingCharacterChat,
                    updateShowUserChatTextField = updateShowUserChatTextField,
                    userChatUiState = userChatUiState
                )
            }
        }

    }
}

@Composable
fun AnswerCharacterChat(
    characterChatUiState: State<CharacterChattingUiState>,
    userChatUiState: State<UserChattingUiState>,
    backgroundColor: Color = Main2,
    textColor: Color = Main3,
    textStyle: TextStyle = OffroadTheme.typography.textContents,
    updateAnswerCharacterChatButtonState: (Boolean) -> Unit,
    updateUserWatchingCharacterChat: (Boolean) -> Unit,
    updateShowUserChatTextField: (Boolean) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Box(contentAlignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(id = R.string.home_chat_answer),
                modifier = Modifier
                    .background(
                        color = backgroundColor,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 14.dp, vertical = 6.dp)
                    .clickableWithoutRipple {
                        updateUserWatchingCharacterChat(true)
                        updateAnswerCharacterChatButtonState(true)
                        updateShowUserChatTextField(true)
                    },
                color = textColor,
                style = textStyle
            )
        }
    }
}

@Composable
fun CharacterChatAnimation(
    characterChatUiState: State<CharacterChattingUiState>,
    userChatUiState: State<UserChattingUiState>,
    updateAnswerCharacterChatButtonState: (Boolean) -> Unit,
    updateCharacterChatExist: (Boolean) -> Unit,
    updateUserWatchingCharacterChat: (Boolean) -> Unit,
    updateShowUserChatTextField: (Boolean) -> Unit,
    navigateToCharacterChatScreen: (Int, String) -> Unit,
) {
    val offsetY = remember { Animatable(-100.dp.value) }
    val coroutineScope = rememberCoroutineScope()
    var dragOffsetY by remember { mutableFloatStateOf(0f) }
    var isSwipedUp by remember { mutableStateOf(false) }

    var isInactive by remember { mutableStateOf(false) }
    val lastDragTime by remember { mutableLongStateOf(System.currentTimeMillis()) }

    LaunchedEffect(characterChatUiState.value.isCharacterChattingExist) { // 제자리
        coroutineScope.launch {
            offsetY.animateTo(
                targetValue = 0.dp.value,
                animationSpec = tween(durationMillis = 500)
            )
        }
    }

    LaunchedEffect(dragOffsetY, characterChatUiState.value.isCharacterChattingExist) { // 3초 후 상승
        if (characterChatUiState.value.isCharacterChattingExist && !characterChatUiState.value.isCharacterChattingLoading) {
            while (true) {
                delay(1000)
                val currentTime = System.currentTimeMillis()
                if (currentTime - lastDragTime > 3000 && dragOffsetY == 0f) {
                    if (!characterChatUiState.value.isUserWatchingCharacterChat) {
                        isInactive = true
                        break
                    }

                }
            }
        }
    }

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .offset(y = offsetY.value.dp + dragOffsetY.dp)
            .padding(start = 24.dp, top = 70.dp, end = 24.dp)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { _, dragAmount ->
                        val newDragOffsetY = dragOffsetY + dragAmount.y
                        if (newDragOffsetY <= 0f) dragOffsetY = newDragOffsetY
                    },
                    onDragEnd = {
                        if (dragOffsetY < -100f) isSwipedUp = true
                        else {
                            coroutineScope.launch {
                                offsetY.animateTo(
                                    targetValue = 0.dp.value,
                                    animationSpec = tween(durationMillis = 300)
                                )
                                dragOffsetY = 0f
                            }
                        }
                    }
                )


            }
    ) {
        CharacterChat(
            characterChatUiState = characterChatUiState,
            updateAnswerCharacterChatButtonState = updateAnswerCharacterChatButtonState,
            updateUserWatchingCharacterChat = updateUserWatchingCharacterChat,
            updateShowUserChatTextField = updateShowUserChatTextField,
            userChatUiState = userChatUiState,
            updateCharacterChatExist = updateCharacterChatExist,
            navigateToCharacterChatScreen = navigateToCharacterChatScreen
        )
    }

    LaunchedEffect(isSwipedUp) { // 사용자가 스와이프해서 캐릭터 채팅 자연스럽게 없애기
        if (isSwipedUp) {
            coroutineScope.launch {
                offsetY.animateTo(
                    targetValue = -100.dp.value,
                    animationSpec = tween(durationMillis = 500)
                )
                updateCharacterChatExist(false)
                dragOffsetY = 0f
                isSwipedUp = false
            }
        }
    }

    LaunchedEffect(isInactive) {
        if (isInactive) {
            coroutineScope.launch {
                offsetY.animateTo(
                    targetValue = -100.dp.value,
                    animationSpec = tween(durationMillis = 500)
                )
                updateCharacterChatExist(false)
                dragOffsetY = 0f
                isInactive = false
            }
        }
    }
}

@Composable
fun showCharacterChat(
    characterChatUiState: State<CharacterChattingUiState>,
    userChatUiState: State<UserChattingUiState>,
    updateAnswerCharacterChatButtonState: (Boolean) -> Unit,
    updateCharacterChatExist: (Boolean) -> Unit,
    updateUserWatchingCharacterChat: (Boolean) -> Unit,
    updateShowUserChatTextField: (Boolean) -> Unit,
    navigateToCharacterChatScreen: (Int, String) -> Unit
) {
    if (characterChatUiState.value.isCharacterChattingExist) {
        CharacterChatAnimation(
            characterChatUiState = characterChatUiState,
            userChatUiState = userChatUiState,
            updateAnswerCharacterChatButtonState = updateAnswerCharacterChatButtonState,
            updateCharacterChatExist = updateCharacterChatExist,
            updateUserWatchingCharacterChat = updateUserWatchingCharacterChat,
            updateShowUserChatTextField = updateShowUserChatTextField,
            navigateToCharacterChatScreen = navigateToCharacterChatScreen
        )
    }
}

const val MAX_CHARACTER_CHAT_LINE = 60