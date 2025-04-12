package com.teamoffroad.feature.recommendplace.presentation.component

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.BtnInactive
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Transparent
import com.teamoffroad.core.designsystem.theme.White
import kotlinx.coroutines.delay

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
