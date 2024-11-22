package com.teamoffroad.feature.mypage.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.feature.mypage.presentation.component.GainedEmblemsHeader
import com.teamoffroad.feature.mypage.presentation.component.GainedEmblemsItems
import com.teamoffroad.feature.mypage.presentation.model.GainedEmblemsResult
import com.teamoffroad.offroad.feature.mypage.R

@Composable
internal fun GainedEmblemsScreen(
    navigateToBack: () -> Unit,
    viewModel: GainedEmblemsViewModel = hiltViewModel(),
) {
    val isEmblemState by viewModel.emblemsUiState.collectAsState()
    val isLoadMoreEmblemsUiState by viewModel.loadMoreEmblemsUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getEmblems()
    }
    Column(
        modifier = Modifier
            .navigationPadding()
            .fillMaxSize()
            .background(Main1)
    ) {
        OffroadActionBar()
        NavigateBackAppBar(
            text = stringResource(R.string.my_page_my_page),
            modifier = Modifier.padding(top = 20.dp)
        ) {
            navigateToBack()
        }
        GainedEmblemsHeader()
        HorizontalDivider(
            color = Gray100,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
        )
        when (isEmblemState.gainedEmblemsValidateResult) {
            GainedEmblemsResult.Success -> {
                GainedEmblemsItems(
                    isEmblemState = isEmblemState,
                    onLoadMore = { viewModel.loadMoreEmblems() },
                    isLoading = isLoadMoreEmblemsUiState
                )
            }

            GainedEmblemsResult.Error -> {
            }

            else -> {
                GainedEmblemsLoading()
            }
        }
    }
}

@Composable
fun GainedEmblemsLoading() {
    Column(
        modifier = Modifier
            .background(ListBg)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(com.teamoffroad.offroad.core.designsystem.R.raw.loading_linear))
        LottieAnimation(
            composition = composition,
        )
    }
}