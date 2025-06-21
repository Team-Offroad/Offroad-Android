package com.teamoffroad.feature.recommendplace.presentation.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.teamoffroad.characterchat.presentation.component.TimeLabel
import com.teamoffroad.characterchat.presentation.component.UserChatBox
import com.teamoffroad.characterchat.presentation.model.ChatModel
import com.teamoffroad.characterchat.presentation.model.ChatType
import com.teamoffroad.characterchat.presentation.model.TimeType
import com.teamoffroad.characterchat.presentation.model.TimeType.AM
import com.teamoffroad.core.designsystem.theme.BtnInactive
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub4

@Composable
fun RecommendPlaceChats(
    name: String,
    isChatLoading: Boolean,
    chatList: List<ChatModel>,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(com.teamoffroad.offroad.core.designsystem.R.raw.loading_linear_sub))
    val animationState =
        animateLottieCompositionAsState(composition, iterations = LottieConstants.IterateForever)

//    if (animationState.isAtEnd && animationState.isPlaying) {
//        LaunchedEffect(Unit) { }
//    }

    LaunchedEffect(chatList.size) {
        if (chatList.isNotEmpty()) {
            listState.animateScrollToItem(chatList.lastIndex)
        }
    }

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        state = listState
    ) {
        items(chatList) { chat ->
            when (chat.chatType) {
                ChatType.USER -> {
                    if (chat.isPlaceRecommendation) {
                        RecommendPlaceUserChatBox(
                            text = chat.text,
                            time = chat.time
                        )
                    } else {
                        UserChatBox(
                            text = chat.text,
                            time = chat.time
                        )
                    }
                }

                ChatType.ORB_CHARACTER -> {
                    RecommendPlaceCharacterChatBox(
                        name = name,
                        text = chat.text,
                        time = chat.time
                    )
                }
            }
        }

        if (isChatLoading) {
            item {
                Row(
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .widthIn(max = 256.dp)
                            .padding(start = 24.dp)
                            .border(
                                width = 1.dp,
                                brush = Brush.horizontalGradient(colors = com.teamoffroad.characterchat.presentation.component.RecommendPlaceStrokeGradientColors),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .background(
                                brush = Brush.horizontalGradient(com.teamoffroad.characterchat.presentation.component.RecommendPlaceFillGradientColors),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(16.dp)
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
                        LottieAnimation(
                            composition = composition,
                            progress = animationState.progress,
                            modifier = Modifier.size(25.dp)
                        )

                    }
                }
            }
        }

//        item {
//            Row(
//                modifier = Modifier.padding(bottom = 16.dp)
//            ) {
//                Row(
//                    modifier = Modifier
//                        .widthIn(max = 256.dp)
//                        .padding(start = 24.dp)
//                        .border(
//                            width = 1.dp,
//                            brush = Brush.horizontalGradient(colors = com.teamoffroad.characterchat.presentation.component.RecommendPlaceStrokeGradientColors),
//                            shape = RoundedCornerShape(12.dp)
//                        )
//                        .background(
//                            color = Color.White,
//                            shape = RoundedCornerShape(12.dp)
//                        )
//                        .background(
//                            brush = Brush.horizontalGradient(com.teamoffroad.characterchat.presentation.component.RecommendPlaceFillGradientColors),
//                            shape = RoundedCornerShape(12.dp)
//                        )
//                        .padding(16.dp)
//                ) {
//                    Text(
//                        text = "오브",
//                        style = OffroadTheme.typography.textBold,
//                        color = Sub4,
//                    )
//                    Text(
//                        text = ": ",
//                        color = Main2,
//                        style = OffroadTheme.typography.textRegular,
//                        modifier = Modifier.padding(start = 4.dp),
//                    )
//                    LottieAnimation(
//                        composition = composition,
//                        progress = animationState.progress,
//                        modifier = Modifier.size(25.dp)
//                    )
//
//                }
//            }
//        }


    }
}

@Composable
fun RecommendPlaceCharacterChatBox(
    name: String,
    text: String,
    time: Triple<TimeType, Int, Int> = Triple(AM, 0, 0),
) {
    Row(
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .widthIn(max = 290.dp)
                .padding(start = 24.dp)
                .border(
                    width = 1.dp,
                    brush = Brush.horizontalGradient(colors = com.teamoffroad.characterchat.presentation.component.RecommendPlaceStrokeGradientColors),
                    shape = RoundedCornerShape(12.dp)
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(
                    brush = Brush.horizontalGradient(com.teamoffroad.characterchat.presentation.component.RecommendPlaceFillGradientColors),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp)
        ) {
            Text(
                text = name,
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
                text = text,
                style = OffroadTheme.typography.textRegular,
                color = Main2,
            )
        }
        TimeLabel(
            modifier = Modifier.align(Alignment.Bottom),
            textStyle = OffroadTheme.typography.textContentsSmall,
            textColor = Main2,
            time = time,
        )
    }
}

@Composable
fun RecommendPlaceUserChatBox(
    text: String,
    time: Triple<TimeType, Int, Int> = Triple(AM, 0, 0),
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.End
    ) {
        TimeLabel(
            modifier = Modifier.align(Alignment.Bottom),
            textStyle = OffroadTheme.typography.textContentsSmall,
            textColor = Main2,
            time = time,
        )
        Row(
            modifier = Modifier
                .widthIn(max = 220.dp)
                .padding(end = 24.dp)
                .border(
                    width = 1.dp,
                    color = BtnInactive,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(
                    color = Main3,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp)
        ) {
            Text(
                text = text,
                style = OffroadTheme.typography.textRegular,
                color = Main2,
                textAlign = TextAlign.End
            )
        }
    }
}