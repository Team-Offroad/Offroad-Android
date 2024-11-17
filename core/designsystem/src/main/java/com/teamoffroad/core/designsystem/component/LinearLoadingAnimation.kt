package com.teamoffroad.core.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.teamoffroad.offroad.core.designsystem.R

@Composable
fun LinearLoadingAnimation(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
) {
    if (isLoading) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_linear))
        LottieAnimation(
            composition = composition,
            iterations = Int.MAX_VALUE,
            modifier = modifier,
        )
    }
}