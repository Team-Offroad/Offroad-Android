package com.teamoffroad.feature.recommendplace.presentation.component

import android.content.Context
import android.graphics.Rect
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
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
import com.teamoffroad.core.designsystem.theme.BtnInactive
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub4
import com.teamoffroad.core.designsystem.theme.Transparent
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.offroad.feature.recommendplace.R
import kotlinx.coroutines.delay

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
    val focusManager = LocalFocusManager.current
    val keyboardHeight = remember { mutableIntStateOf(0) }
    val view = LocalView.current

    DisposableEffect(view) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val r = Rect()
            view.getWindowVisibleDisplayFrame(r)
            val height = view.rootView.height
            val keypadHeight = height - r.bottom
            keyboardHeight.intValue = if (keypadHeight > height * 0.15) keypadHeight else 0
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }

    LaunchedEffect(Unit) {
        characterChatViewModel.updateIsChatting(true)
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
                bottomPadding = if (isChatting.value) keyboardHeight.intValue else 0,
                isChatting = isChatting.value,
                isSending = uiState.value.isSending,
                isLoadable = uiState.value.isLoadable,
                updateChats = characterChatViewModel::handleChatState,
                updateIsChatting = characterChatViewModel::updateIsChatting,
                navigateToRecommendPlace = { }
            )

            RecommendChatTextField(
                modifier = Modifier,
                keyboardHeight = keyboardHeight.intValue
            )
        }

        FullLinearLoadingAnimation(isLoading = uiState.value.isLoading && uiState.value.chats.values.isEmpty())
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendChatTextField(modifier: Modifier, keyboardHeight: Int) {
    val text = remember { mutableStateOf("") }
    val textFieldHeight = remember { mutableIntStateOf(0) }
    val focusRequester = remember { FocusRequester() }
    val context = LocalView.current.context

    LaunchedEffect(Unit) {
        delay(300)
        focusRequester.requestFocus()
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    Box(
        modifier = modifier
            .padding(bottom = with(LocalDensity.current) { keyboardHeight.toDp() })
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = White,
                shape = RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp)
            )
            .padding(horizontal = 22.dp, vertical = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(with(LocalDensity.current) { textFieldHeight.intValue.toDp() })
                .align(Alignment.BottomCenter)
                .padding(vertical = 10.dp)
                .padding(end = 44.dp)
                .background(
                    color = BtnInactive,
                    shape = RoundedCornerShape(10.dp),
                ),
        )

        TextField(
            value = text.value,
            onValueChange = {
                text.value = it
            },
            textStyle = OffroadTheme.typography.textRegular,
            modifier = Modifier
                .padding(end = 44.dp)
                .padding(horizontal = 2.dp)
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onGloballyPositioned { layoutCoordinates ->
                    textFieldHeight.intValue = layoutCoordinates.size.height
                },
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
            contentDescription = null,
            modifier = Modifier
                .padding(end = 2.dp)
                .size(36.dp)
                .align(Alignment.CenterEnd)
                .clickableWithoutRipple {
                    if (text.value.isNotBlank()) {
                        text.value = ""
                    }
                },
        )
    }
}
