package com.teamoffroad.feature.main.component

import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.BtnInactive
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Transparent
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.main.UserChattingUiState
import com.teamoffroad.offroad.feature.home.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeUserChatTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    userChatUiState: State<UserChattingUiState>,
    updateShowUserChatTextField: (Boolean) -> Unit,
    keyboard: Boolean,
    onValueChange: (String) -> Unit = {},
    onSendClick: () -> Unit = {},
) {
    val scrollState = rememberScrollState()
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val contextView = LocalView.current
    var showLottieLoading by remember { mutableStateOf(false) }

    var keyboardVisible by remember { mutableStateOf(keyboard) }

    LaunchedEffect(userChatUiState.value.showUserChatTextField) {
        if (userChatUiState.value.showUserChatTextField) {
            focusRequester.requestFocus()
        }
    }

    LaunchedEffect(keyboardVisible) {
        if (!keyboardVisible) {
            focusManager.clearFocus()
//            isChatting.value = false
//            isCharacterChatting(false)
            updateShowUserChatTextField(false)
        }
    }

    DisposableEffect(contextView) {
        val rect = Rect()
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            contextView.getWindowVisibleDisplayFrame(rect)
            val screenHeight = contextView.rootView.height
            val keypadHeight = screenHeight - rect.bottom
            keyboardVisible = keypadHeight > screenHeight * 0.15
        }
        contextView.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            contextView.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }

    AnimatedVisibility(
        visible = userChatUiState.value.showUserChatTextField,
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    color = White,
                    shape = RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp)
                )
                .padding(horizontal = 22.dp, vertical = 4.dp),
        ) {
            val textFieldHeight = remember { mutableIntStateOf(0) }

            Column{
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp, bottom = 4.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.home_chat_me),
                        color = Main2,
                        style = OffroadTheme.typography.textBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 6.dp)
                    )
                    Box{
                        if (text.isNotBlank()) {
                            Box(
                                modifier = Modifier
                                    .size(width = 54.dp, height = 27.dp)
                            ) {
                                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(com.teamoffroad.offroad.core.designsystem.R.raw.loading_linear))
                                val animationState = animateLottieCompositionAsState(composition, iterations = LottieConstants.IterateForever)

                                if (animationState.isAtEnd && animationState.isPlaying) {
                                    LaunchedEffect(Unit) {  }
                                }

                                LottieAnimation(composition, animationState.progress)
                            }
                        } else {
                            Text(
                                text = userChatUiState.value.chatContent,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp)
                                    .onGloballyPositioned { layoutCoordinates ->
                                        textFieldHeight.intValue = layoutCoordinates.size.height
                                    },
                                style = OffroadTheme.typography.textRegular,
                                maxLines = 2,
                            )
                        }
                    }
                }

                Box {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(with(LocalDensity.current) { textFieldHeight.intValue.toDp() })
                            .align(Alignment.Center)
                            .padding(vertical = 10.dp)
                            .padding(end = 44.dp)
                            .background(
                                color = BtnInactive,
                                shape = RoundedCornerShape(10.dp),
                            ),
                    )
                    TextField(
                        value = text,
                        onValueChange = { onValueChange(it) },
                        textStyle = OffroadTheme.typography.textRegular,
                        modifier = Modifier
                            .verticalScroll(scrollState)
                            .padding(end = 44.dp)
                            .padding(horizontal = 2.dp)
                            .fillMaxWidth()
                            .focusRequester(focusRequester)
                            .onGloballyPositioned { layoutCoordinates ->
                                textFieldHeight.intValue = layoutCoordinates.size.height
                            },
//                            .onFocusChanged { focusState ->
//                                onFocusChange(focusState.isFocused)
//                            },
                        maxLines = 2,
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Transparent,
                            focusedIndicatorColor = Transparent,
                            unfocusedIndicatorColor = Transparent,
                            focusedTextColor = Main2,
                        ),
                        shape = RoundedCornerShape(12.dp),
                    )
                    Image(
                        painter = painterResource(id = com.teamoffroad.offroad.feature.characterchat.R.drawable.ic_character_chat_send),
                        contentDescription = "send",
                        modifier = Modifier
                            .padding(end = 2.dp)
                            .size(36.dp)
                            .align(Alignment.CenterEnd)
                            .clickableWithoutRipple { if (text.isNotBlank()) onSendClick() },
                    )
                }
            }

        }
    }
}
