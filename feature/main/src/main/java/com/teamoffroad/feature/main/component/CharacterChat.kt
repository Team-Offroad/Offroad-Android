package com.teamoffroad.feature.main.component

import android.util.Log
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.BtnInactive
import com.teamoffroad.core.designsystem.theme.Kakao
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub4
import com.teamoffroad.feature.main.CharacterChattingUiState
import com.teamoffroad.feature.main.UserChattingUiState
import com.teamoffroad.offroad.feature.home.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CharacterChat(
//    isCharacterChattingLoading: State<Boolean>,
    characterChatUiState: State<CharacterChattingUiState>,
    characterTextColor: Color = Sub4,
    characterTextStyle: TextStyle = OffroadTheme.typography.textBold,
    messageTextColor: Color = Main2,
    messageTextStyle: TextStyle = OffroadTheme.typography.textRegular,
    backgroundColor: Color = Main3,
    borderColor: Color = BtnInactive,
    updateAnswerCharacterChatButtonState: (Boolean) -> Unit,
    updateUserWatchingCharacterChat: (Boolean) -> Unit,
    updateShowUserChatTextField: (Boolean) -> Unit,
    //navigateToCharacterChatScreen: (Int, String) -> Unit
) {
    val checkCharacterChattingLines = remember { mutableStateOf(false) }
    val hasCheckedCharacterChattingLines = remember { mutableStateOf(false) }
    val isExpanded = remember { mutableStateOf(false) }

    val rotationAngle by animateFloatAsState(
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
                //navigateToCharacterChatScreen(-1, characterName)
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

//                if (isCharacterChattingLoading.value) {
//                    Box(
//                        modifier = Modifier
//                            .size(width = 54.dp, height = 27.dp)
//                    ) {
//                        val composition by rememberLottieComposition(
//                            LottieCompositionSpec.RawRes(
//                                com.teamoffroad.offroad.core.designsystem.R.raw.loading_linear
//                            )
//                        )
//                        val animationState = animateLottieCompositionAsState(
//                            composition,
//                            iterations = LottieConstants.IterateForever
//                        )
//
//                        if (animationState.isAtEnd && animationState.isPlaying) {
//                            LaunchedEffect(Unit) { }
//                        }
//
//                        LottieAnimation(composition, animationState.progress)
//                    }
//                } else {
                    Text(
                        text = characterChatUiState.value.characterChatContent,
                        modifier = Modifier.weight(1f),
                        color = messageTextColor,
                        style = messageTextStyle,
                        onTextLayout = { textLayoutResult ->
                            if (!hasCheckedCharacterChattingLines.value) {
                                checkCharacterChattingLines.value = textLayoutResult.lineCount >= 3
                                hasCheckedCharacterChattingLines.value = true
                            }
                        },
                        maxLines = if (!isExpanded.value && checkCharacterChattingLines.value) 2 else Int.MAX_VALUE,
                        overflow = TextOverflow.Ellipsis,
                    )
//
                    if(checkCharacterChattingLines.value) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_home_accordian),
                            contentDescription = "accordion down",
                            modifier = Modifier
                                .graphicsLayer(rotationX = rotationAngle)
                                .clickableWithoutRipple {
                                    // 이때도 3초 이후에 채팅이 올라가면 안딤
                                    updateUserWatchingCharacterChat(true)
                                    isExpanded.value = !isExpanded.value
                                }
                        )
                    }

//                }

            }


            if (characterChatUiState.value.isCharacterChattingExist) {
                AnswerCharacterChat(
                    characterChatUiState = characterChatUiState,
                    updateAnswerCharacterChatButtonState = updateAnswerCharacterChatButtonState,
                    updateUserWatchingCharacterChat = updateUserWatchingCharacterChat,
                    updateShowUserChatTextField = updateShowUserChatTextField,
                )
            }
        }

    }
}

@Composable
fun AnswerCharacterChat(
    characterChatUiState: State<CharacterChattingUiState>,
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
//    isCharacterChatting: State<Boolean>,
//    isChatting: MutableState<Boolean>,
//    isCharacterChattingLoading: State<Boolean>,
//    answerCharacterChat: MutableState<Boolean>,
    characterChatUiState: State<CharacterChattingUiState>,
    userChatUiState: State<UserChattingUiState>,
    updateAnswerCharacterChatButtonState: (Boolean) -> Unit,
    updateCharacterChatExist: (Boolean) -> Unit,
    updateUserWatchingCharacterChat: (Boolean) -> Unit,
    updateShowUserChatTextField: (Boolean) -> Unit,
    //updateCharacterChatting: (Boolean) -> Unit,
    //navigateToCharacterChatScreen: (Int, String) -> Unit
) {
    val offsetY = remember { Animatable(-10.dp.value) }
    val coroutineScope = rememberCoroutineScope()
    var dragOffsetY by remember { mutableFloatStateOf(0f) }
    var isSwipedUp by remember { mutableStateOf(false) }

    var isInactive by remember { mutableStateOf(false) }
    var lastDragTime by remember { mutableStateOf(System.currentTimeMillis()) }

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
                        if (dragOffsetY < -50f) isSwipedUp = true
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
//            isChatting = isChatting,
//            isCharacterChattingLoading = isCharacterChattingLoading,
            //answerCharacterChat = answerCharacterChat,
            characterChatUiState = characterChatUiState,
            updateAnswerCharacterChatButtonState = updateAnswerCharacterChatButtonState,
            updateUserWatchingCharacterChat = updateUserWatchingCharacterChat,
            updateShowUserChatTextField = updateShowUserChatTextField,
            //navigateToCharacterChatScreen = navigateToCharacterChatScreen
        )
    }

    LaunchedEffect(isSwipedUp) { // 사용자가 스와이프해서 캐릭터 채팅 자연스럽게 없애기
        if (isSwipedUp) {
            coroutineScope.launch {
                offsetY.animateTo(
                    targetValue = -50.dp.value,
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
                    targetValue = -50.dp.value,
                    animationSpec = tween(durationMillis = 500)
                )
                updateCharacterChatExist(false)
                dragOffsetY = 0f
                isInactive = false
            }
        }
    }
}