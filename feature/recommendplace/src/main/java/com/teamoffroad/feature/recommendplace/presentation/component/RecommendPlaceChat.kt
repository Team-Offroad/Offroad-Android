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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.characterchat.presentation.model.ChatModel
import com.teamoffroad.characterchat.presentation.model.ChatModel.Companion.toTime
import com.teamoffroad.characterchat.presentation.model.ChatType
import com.teamoffroad.core.designsystem.component.actionBarPadding
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.offroad.feature.recommendplace.R
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun RecommendPlaceChat(
    name: String,
    isChatLoading: Boolean,
    chatList: Map<LocalDate, List<ChatModel>>,
    updateOrderChats: (ChatModel) -> Unit,
    getPlaceRecommendationsOrderChats: (String) -> Unit,
    getPlaceRecommendations: () -> Unit,
    onClose: () -> Unit,
    resetPlaceRecommendationsChats: () -> Unit,
) {
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
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_recommend_place_close),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 10.dp, end = 20.dp)
                    .clickableWithoutRipple {
                        resetPlaceRecommendationsChats()
                        getPlaceRecommendations()
                        onClose()
                    }
            )

            RecommendPlaceChats(
                name = name,
                isChatLoading = isChatLoading,
                modifier = Modifier.weight(1f),
                chatList = chatList.values.flatten(),
            )

            RecommendPlaceExampleQuestionButton(
                onClick = { question ->
                    updateOrderChats(
                        ChatModel(
                            text = question,
                            time = LocalDateTime.now().toString().toTime(),
                            chatType = ChatType.USER,
                            isPlaceRecommendation = true
                        )
                    )
                    getPlaceRecommendationsOrderChats(question)
                }
            )

            RecommendChatTextField(
                modifier = Modifier,
                keyboardHeight = keyboardHeight.intValue,
                updateOrderChats = updateOrderChats,
                getPlaceRecommendationsOrderChats = getPlaceRecommendationsOrderChats
            )
        }
    }
}