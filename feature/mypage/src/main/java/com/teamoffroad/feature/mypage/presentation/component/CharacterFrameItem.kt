package com.teamoffroad.feature.mypage.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.mypage.presentation.model.CharacterModel

@Composable
fun CharacterFrameItem(
    modifier: Modifier = Modifier,
    characterModel: CharacterModel = CharacterModel.dummyCharacter,
    isLottieImage: Boolean = false,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(142f / 194f)
            .background(color = Color(characterModel.characterFrameColor), shape = RoundedCornerShape(size = 10.dp))
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(
                    color = Color(characterModel.characterBackgroundColor),
                    shape = RoundedCornerShape(size = 10.dp)
                )
        ) {
            val composition by rememberLottieComposition(
                spec = LottieCompositionSpec.Url(characterModel.characterThumbnailImageUrl) // TODO: 링크 변경
            )

            val progress by animateLottieCompositionAsState(
                composition = composition,
                iterations = LottieConstants.IterateForever
            )

            when (isLottieImage) {
                true -> {
                    LottieAnimation(
                        composition = composition,
                        progress = progress,
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 30.dp)
                            .align(Alignment.BottomCenter)
                    )
                }

                false -> {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(characterModel.characterThumbnailImageUrl)
                            .decoderFactory(SvgDecoder.Factory())
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(horizontal = 28.dp)
                            .padding(bottom = 12.dp)
                            .fillMaxWidth(),
                    )
                }
            }
        }
        Text(
            text = characterModel.characterName,
            style = OffroadTheme.typography.textContents,
            color = White,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 10.dp, bottom = 2.dp)
        )
    }
}
