package com.teamoffroad.feature.home.presentation.component.character

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
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.text.substring
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.BtnInactive
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub4
import com.teamoffroad.offroad.feature.home.R
import kotlinx.coroutines.launch

@Composable
fun CharacterChat(
    isChatting: MutableState<Boolean>,
    isCharacterChattingLoading: State<Boolean>,
    answerCharacterChat: MutableState<Boolean>,
    characterName: String,
    characterContent: String,
    characterTextColor: Color = Sub4,
    characterTextStyle: TextStyle = OffroadTheme.typography.textBold,
    messageTextColor: Color = Main2,
    messageTextStyle: TextStyle = OffroadTheme.typography.textRegular,
    backgroundColor: Color = Main3,
    borderColor: Color = BtnInactive,
    navigateToCharacterChatScreen: (Int, String) -> Unit
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
                navigateToCharacterChatScreen(-1, characterName)
            }
    ) {
        Column {
            Row {
                Text(
                    text = "$characterName : ",
                    modifier = Modifier,
                    color = characterTextColor,
                    style = characterTextStyle
                )

                if (isCharacterChattingLoading.value) {
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
                    Text(
                        text = characterContent,
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

                    if(checkCharacterChattingLines.value) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_home_accordian),
                            contentDescription = "accordion down",
                            modifier = Modifier
                                .graphicsLayer(rotationX = rotationAngle)
                                .clickableWithoutRipple {
                                    isExpanded.value = !isExpanded.value
                                }
                        )
                    }

                }

            }

            if (!answerCharacterChat.value) {
                AnswerCharacterChat(isChatting = isChatting)
            }
        }

    }
}

@Composable
fun AnswerCharacterChat(
    isChatting: MutableState<Boolean>,
    backgroundColor: Color = Main2,
    textColor: Color = Main3,
    textStyle: TextStyle = OffroadTheme.typography.textContents
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
                        isChatting.value = true
                    },
                color = textColor,
                style = textStyle
            )
        }
    }
}

@Composable
fun CharacterChatAnimation(
    isCharacterChatting: State<Boolean>,
    isChatting: MutableState<Boolean>,
    isCharacterChattingLoading: State<Boolean>,
    answerCharacterChat: MutableState<Boolean>,
    characterName: String,
    characterContent: String,
    updateCharacterChatting: (Boolean) -> Unit,
    navigateToCharacterChatScreen: (Int, String) -> Unit
) {
    val offsetY = remember { Animatable(-10.dp.value) }
    val coroutineScope = rememberCoroutineScope()
    var dragOffsetY by remember { mutableFloatStateOf(0f) }
    var isSwipedUp by remember { mutableStateOf(false) }

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
            isChatting = isChatting,
            isCharacterChattingLoading = isCharacterChattingLoading,
            answerCharacterChat = answerCharacterChat,
            characterName = characterName,
            characterContent = characterContent,
            navigateToCharacterChatScreen = navigateToCharacterChatScreen
        )
    }

    LaunchedEffect(isSwipedUp) {
        if (isSwipedUp) {
            coroutineScope.launch {
                offsetY.animateTo(
                    targetValue = -50.dp.value,
                    animationSpec = tween(durationMillis = 500)
                )
                updateCharacterChatting(false)
                dragOffsetY = 0f
                isSwipedUp = false
            }
        }
    }
}