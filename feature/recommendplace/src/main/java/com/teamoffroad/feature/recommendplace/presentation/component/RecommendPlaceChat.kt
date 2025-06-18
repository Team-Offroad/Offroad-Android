package com.teamoffroad.feature.recommendplace.presentation.component

import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.characterchat.presentation.model.ChatModel
import com.teamoffroad.characterchat.presentation.model.ChatModel.Companion.toTime
import com.teamoffroad.characterchat.presentation.model.ChatType
import com.teamoffroad.core.designsystem.component.actionBarPadding
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.offroad.feature.recommendplace.R
import java.time.LocalDateTime

@Composable
fun RecommendPlaceChat(
    name: String,
    text: String,
    time: String,
    onClose: () -> Unit
) {
    val keyboardHeight = remember { mutableIntStateOf(0) }
    val view = LocalView.current
    val userChatMessages = remember { mutableStateListOf<String>() }

    val initialChat = ChatModel( // 환영 메시지로 초기 chatList 세팅
        text = stringResource(id = R.string.recommend_place_button_temp_welcome_text),
        time = LocalDateTime.now().toString().toTime(),
        chatType = ChatType.ORB_CHARACTER,
        isPlaceRecommendation = false
    )
    val chatList = remember { mutableStateListOf(initialChat) }


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

    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.horizontalGradient(RecommendPlaceFillGradientColors))
            .let { base ->
                if (keyboardHeight.intValue === 0) base.navigationPadding() else base
            }
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
                    .padding(end = 20.dp)
                    .clickableWithoutRipple { onClose() }
            )

            RecommendPlaceChats(
                name = name,
                modifier = Modifier.weight(1f),
                chatList = chatList,
                userChatMessages = userChatMessages
            )

            RecommendPlaceExampleQuestionButton(
                onClick = { question ->
                    userChatMessages.add(question)
                    val currentTimeTriple = LocalDateTime.now().toString().toTime()
                    chatList.add(
                        ChatModel(
                            text = question,
                            time = currentTimeTriple,
                            chatType = ChatType.USER,
                            isPlaceRecommendation = true
                        )
                    )
                }
            )

            RecommendChatTextField(
                modifier = Modifier,
                keyboardHeight = keyboardHeight.intValue
            )
        }
    }
}
