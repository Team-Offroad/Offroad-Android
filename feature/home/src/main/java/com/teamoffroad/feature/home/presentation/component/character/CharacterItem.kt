package com.teamoffroad.feature.home.presentation.component.character

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.teamoffroad.core.designsystem.component.OffroadTagItem
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.Kakao
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.core.designsystem.theme.Sub55
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.home.presentation.HomeViewModel
import com.teamoffroad.feature.home.presentation.component.UiState
import com.teamoffroad.feature.home.presentation.component.dialog.ChangeEmblemDialog
import com.teamoffroad.feature.home.presentation.model.UserChangeEmblemDialogStateModel
import com.teamoffroad.offroad.feature.home.R

class CharacterItem {

    @Composable
    fun CharacterImage(
        viewModel: HomeViewModel,
        context: Context,
    ) {
        val baseCharacterImage = viewModel.baseCharacterImage.collectAsState().value
        val motionCharacterUrl = viewModel.motionCharacterUrl.collectAsState().value

        Box(
            modifier = Modifier
                .aspectRatio(280f / 280f),
            contentAlignment = Alignment.Center
        ) {
            if (motionCharacterUrl == null) {
                Box(
                    modifier = Modifier
                        .padding(top = 120.dp)
                        .fillMaxHeight()
                        .align(Alignment.BottomCenter)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(baseCharacterImage)
                            .decoderFactory(SvgDecoder.Factory())
                            .build(),
                        contentDescription = "explorer",
                        modifier = Modifier
                            .aspectRatio(210f / 210f)
                            .fillMaxSize()
                            .align(Alignment.BottomCenter),
                        // TODO: placeholder, error일 때
                    )
                }
            } else {
                val composition by rememberLottieComposition(
                    spec = LottieCompositionSpec.Url(motionCharacterUrl)
                )

                val progress by animateLottieCompositionAsState(
                    composition = composition,
                    iterations = LottieConstants.IterateForever
                )

                val lottieAnimatable = rememberLottieAnimatable()

                LaunchedEffect(composition) {
                    lottieAnimatable.animate(
                        composition = composition,
                        initialProgress = 0f
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 140.dp)
                        .aspectRatio(210f / 210f)
                ) {
                    LottieAnimation(
                        composition = composition,
                        progress = progress,
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.BottomCenter)
                    )
                }
            }
        }
    }

    @Composable
    fun CharacterNameText(
        name: String,
        backgroundColor: Color = Sub55,
        borderColor: Color = Sub
    ) {
        Text(
            style = OffroadTheme.typography.subtitle2Semibold,
            text = name,
            modifier = Modifier
                .padding(start = 24.dp, top = 12.dp)
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(20.dp)
                )
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(20.dp),
                    color = borderColor
                )
                .padding(horizontal = 16.dp)
                .padding(vertical = 6.dp),
            color = White
        )
    }

    @SuppressLint("UnrememberedMutableState")
    @Composable
    fun EmblemNameText(
        context: Context,
        modifier: Modifier = Modifier,
    ) {
        val viewModel: HomeViewModel = hiltViewModel()
        val emblemState = viewModel.patchEmblemState.collectAsState(initial = UiState.Loading).value
        val userEmblem = viewModel.selectedEmblem.collectAsState().value
        val userChangeEmblemDialogStateModel =
            remember { mutableStateOf<UserChangeEmblemDialogStateModel?>(null) }
        val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }

        val isChangeEmblemDialogShown = remember { mutableStateOf(false) }

        when (emblemState) {
            is UiState.Success -> null
            is UiState.Failure -> {
                Toast.makeText(context, emblemState.errorMessage, Toast.LENGTH_SHORT).show()
                null
            }

            else -> null
        }

        Box(
            modifier = modifier
                .padding(horizontal = 24.dp)
                .aspectRatio(312f / 38f)
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            OffroadTagItem(text = userEmblem)
            Image(
                painter = painterResource(id = R.drawable.ic_home_change_title),
                contentDescription = "change title",
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp, end = 20.dp)
                    .clickableWithoutRipple(
                        interactionSource = interactionSource
                    ) {
                        isChangeEmblemDialogShown.value = true
                    }
            )

            if (isChangeEmblemDialogShown.value) {
                ChangeEmblemDialog(
                    showDialog = isChangeEmblemDialogShown,
                    userChangeEmblemDialogStateModel = userChangeEmblemDialogStateModel,
                    originEmblem = userEmblem,
                    onClickCancel = {
                        isChangeEmblemDialogShown.value = false
                        userChangeEmblemDialogStateModel.value?.onClickCancel
                    },
                    onCharacterChange = { emblem ->
                        if (emblem != null) {
                            viewModel.patchEmblem(emblem)
                        }
                    }
                )
            }
        }
    }
}