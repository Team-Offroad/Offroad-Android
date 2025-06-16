package com.teamoffroad.feature.recommendplace.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.actionBarPadding
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.feature.recommendplace.presentation.component.RecommendPlaceBody
import com.teamoffroad.feature.recommendplace.presentation.component.RecommendPlaceButton
import com.teamoffroad.feature.recommendplace.presentation.component.RecommendPlaceChat
import com.teamoffroad.feature.recommendplace.presentation.component.RecommendPlaceHeader
import com.teamoffroad.offroad.feature.recommendplace.R


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RecommendPlaceScreen(
    hasChatted: Boolean,
    content: String,
    navigateToBack: () -> Unit,
    navigateToOrderRecommendPlace: () -> Unit,
    recommendPlaceViewModel: RecommendPlaceViewModel = hiltViewModel(),
) {
    val isButtonVisible = remember { mutableStateOf(true) }
    var isRecommendPlaceViewExpanded by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()
    val placeRecommendationsUiState = recommendPlaceViewModel.placeRecommendationsUiState.collectAsStateWithLifecycle()

    val transitionState = remember { MutableTransitionState(false) }
    transitionState.targetState = isRecommendPlaceViewExpanded

    LaunchedEffect(Unit) {
        recommendPlaceViewModel.getPlaceRecommendations()
    }

    LaunchedEffect(listState.firstVisibleItemIndex) {
        isButtonVisible.value = listState.firstVisibleItemIndex == 0
    }

    AnimatedContent(
        targetState = isRecommendPlaceViewExpanded,
        transitionSpec = {
            fadeIn() with fadeOut()
        },
        label = "recommendPlaceContentTransition"
    ) { isExpanded ->
        if (isExpanded) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .actionBarPadding()
                    .background(color = Main1)
            ) {
                RecommendPlaceChat(
                    name = "추천 장소", // 캐릭터 이름으로 넣기
                    text = content,
                    time = "오전 11:30", // 현재 시간으로
                    onClose = { isRecommendPlaceViewExpanded = false }
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .navigationPadding()
                    .background(color = Main1)
                    .actionBarPadding()
            ) {
                NavigateBackAppBar(
                    text = stringResource(id = R.string.recommend_place_back_to_home),
                    modifier = Modifier.padding(top = 20.dp)
                ) { navigateToBack() }
                RecommendPlaceHeader()
                AnimatedVisibility(visible = isButtonVisible.value) {
                    RecommendPlaceButton(
                        hasChatted = hasChatted,
                        content = content,
                        onClick = { isRecommendPlaceViewExpanded = true },
                        navigateToOrderRecommendPlace = navigateToOrderRecommendPlace
                    )
                }
                RecommendPlaceBody(hasChatted, listState, isButtonVisible, placeRecommendationsUiState.value, recommendPlaceViewModel,
                    onClick = { isRecommendPlaceViewExpanded = true} )
            }
        }

    }
}