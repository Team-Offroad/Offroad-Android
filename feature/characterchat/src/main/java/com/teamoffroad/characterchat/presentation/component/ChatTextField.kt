package com.teamoffroad.characterchat.presentation.component

import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.BtnInactive
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Transparent
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.offroad.feature.characterchat.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    isChatting: Boolean = false,
    onValueChange: (String) -> Unit = {},
    onFocusChange: (Boolean) -> Unit = {},
    onSendClick: () -> Unit = {},
) {
    val scrollState = rememberScrollState()
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val contextView = LocalView.current

    var keyboardVisible by remember { mutableStateOf(false) }
    val keyboardHeight = remember { mutableIntStateOf(0) }

    LaunchedEffect(isChatting) {
        if (isChatting) {
            focusRequester.requestFocus()
        }
    }

    LaunchedEffect(keyboardVisible) {
        if (!keyboardVisible) {
            focusManager.clearFocus()
        }
    }

    DisposableEffect(contextView) {
        val rect = Rect()
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            contextView.getWindowVisibleDisplayFrame(rect)
            val screenHeight = contextView.rootView.height
            val keypadHeight = screenHeight - rect.bottom
            keyboardHeight.value = if (keypadHeight > screenHeight * 0.15) keypadHeight else 0

            keyboardVisible = keypadHeight > screenHeight * 0.15
        }
        contextView.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            contextView.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }

    AnimatedVisibility(
        visible = isChatting,
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
                    }
                    .onFocusChanged { focusState ->
                        onFocusChange(focusState.isFocused)
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
                painter = painterResource(id = R.drawable.ic_character_chat_send),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 2.dp)
                    .size(36.dp)
                    .align(Alignment.CenterEnd)
                    .clickableWithoutRipple {
                        if (text.isNotBlank()) onSendClick()
                    },
            )
        }
    }
}
