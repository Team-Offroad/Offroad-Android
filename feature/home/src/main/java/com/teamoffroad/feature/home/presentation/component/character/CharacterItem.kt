package com.teamoffroad.feature.home.presentation.component.character

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
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
import com.teamoffroad.core.designsystem.theme.Black15
import com.teamoffroad.core.designsystem.theme.CharacterName
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.home.presentation.HomeViewModel
import com.teamoffroad.feature.home.presentation.UiState
import com.teamoffroad.feature.home.presentation.component.dialog.OffroadDialog
import com.teamoffroad.feature.home.presentation.model.CustomTitleDialogStateModel
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
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            if (motionCharacterUrl == null) {
                Box(
                    modifier = Modifier
                        .width(300.dp)
                        .padding(top = 140.dp)
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

                Box {
                    LottieAnimation(
                        composition = composition,
                        progress = progress,
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.BottomCenter)
                    )
                }
            }
        }
    }

    @Composable
    fun CharacterNameText(name: String) {
        Text(
            style = OffroadTheme.typography.subtitle2Semibold,
            text = name,
            modifier = Modifier
                .padding(start = 24.dp, top = 12.dp)
                .background(
                    color = CharacterName,
                    shape = RoundedCornerShape(6.dp)
                )
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(6.dp),
                    color = Black15
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
        val selectedEmblem = viewModel.selectedEmblem.collectAsState().value
        val customTitleDialogStateModel =
            remember { mutableStateOf<CustomTitleDialogStateModel?>(null) }
        val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }

        val showDialog = remember { mutableStateOf(false) }

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
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            OffroadTagItem(
                text = selectedEmblem,
                textColor = White,
                style = OffroadTheme.typography.subtitle2Semibold,
                backgroundColor = Sub
            )
            Image(
                painter = painterResource(id = R.drawable.ic_home_change_title),
                contentDescription = "change title",
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 4.dp, end = 6.dp)
                    .clickableWithoutRipple(
                        interactionSource
                    ) {
                        showDialog.value = true
                    }
            )
            if (showDialog.value) {
                OffroadDialog(
                    showDialog,
                    customTitleDialogStateModel,
                    onClickCancel = {
                        showDialog.value = false
                        customTitleDialogStateModel.value?.onClickCancel
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