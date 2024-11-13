package com.teamoffroad.feature.mypage.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.feature.mypage.presentation.model.GainedEmblemsUiState
import kotlinx.coroutines.flow.collectLatest

@Composable
fun GainedEmblemsItems(
    modifier: Modifier = Modifier,
    isEmblemState: GainedEmblemsUiState,
    onLoadMore: () -> Unit,
    isLoading: Boolean,
) {
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow {
            val isAtEnd =
                listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == listState.layoutInfo.totalItemsCount - 1
            isAtEnd
        }.collectLatest { isAtEnd ->
            if (isAtEnd) {
                onLoadMore()
            }
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(ListBg),
        state = listState,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(4.dp))
        }
        items(isEmblemState.emblemList) {
            EmblemContainer(
                title = it.emblemTitle,
                subTitle = it.emblemSubtitle,
                isNew = it.isNew,
                isLock = it.isLock
            )
        }
        if (isLoading) {
            item {
                OnMoreEmblemsLoading()
            }
        }
        item {
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
fun OnMoreEmblemsLoading() {
    Column(
        modifier = Modifier
            .background(ListBg)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(com.teamoffroad.offroad.core.designsystem.R.raw.loading_circle))
        LottieAnimation(
            modifier = Modifier
                .size(50.dp),
            composition = composition,
        )
    }
}