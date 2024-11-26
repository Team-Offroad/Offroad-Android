package com.teamoffroad.feature.mypage.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.AdaptationImage
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.Black
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.offroad.feature.mypage.R

@Composable
fun CharacterFrameItem(
    modifier: Modifier = Modifier,
    characterLabel: String = "",
    characterMainColor: Long = 0,
    characterFrameColor: Long = 0,
    characterThumbnailImageUrl: String = "",
    isGained: Boolean = false,
    isRepresentative: Boolean = false,
    isNewGained: Boolean = false,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(142f / 194f)
            .background(color = Color(characterFrameColor), shape = RoundedCornerShape(size = 10.dp))
            .clickableWithoutRipple(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(
                        color = Color(characterMainColor),
                        shape = RoundedCornerShape(size = 10.dp)
                    )
            ) {
                AdaptationImage(
                    imageUrl = characterThumbnailImageUrl,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 14.dp)
                        .fillMaxWidth(),
                )
            }
            Text(
                text = characterLabel,
                style = OffroadTheme.typography.textContents,
                color = White,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp, bottom = 2.dp),
            )
        }
        when {
            isRepresentative -> {
                RepresentativeTopIcon(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                )
            }

            isNewGained -> {
                NewGainedTopIcon(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                )
            }

            !isGained -> {
                CharacterLockedCover()
            }
        }
    }
}

@Composable
private fun RepresentativeTopIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_my_page_representative),
        contentDescription = "",
        modifier = modifier
            .padding(18.dp)
            .size(24.dp),
    )
}

@Composable
private fun NewGainedTopIcon(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_my_page_new),
        contentDescription = "",
        modifier = modifier
            .padding(18.dp)
            .size(24.dp),
    )
}

@Composable
private fun CharacterLockedCover() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Black.copy(alpha = 0.25f), shape = RoundedCornerShape(size = 10.dp))
            .padding(horizontal = 66.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_my_page_character_locked),
            contentDescription = null,
            alignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 32.dp)
        )
    }
}
