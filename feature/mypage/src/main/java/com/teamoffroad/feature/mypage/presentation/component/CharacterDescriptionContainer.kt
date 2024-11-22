package com.teamoffroad.feature.mypage.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.AdaptationImage
import com.teamoffroad.core.designsystem.theme.Contents2
import com.teamoffroad.core.designsystem.theme.Gray300
import com.teamoffroad.core.designsystem.theme.Gray400
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub4
import com.teamoffroad.feature.mypage.presentation.model.CharacterDetailUiState
import com.teamoffroad.offroad.feature.mypage.R

@Composable
fun CharacterDescriptionContainer(uiState: CharacterDetailUiState) {
    Column(
        modifier = Modifier
            .padding(top = 24.dp)
            .padding(horizontal = 24.dp)
            .fillMaxWidth(),
    ) {
        CharacterNameItem(uiState)
        CharacterIntroductionItem(uiState.characterDetailModel.characterDescription)
    }
}

@Composable
private fun CharacterNameItem(uiState: CharacterDetailUiState) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Main1, shape = RoundedCornerShape(10.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 22.dp)
                .padding(vertical = 16.dp)
        ) {
            AdaptationImage(
                imageUrl = uiState.characterDetailModel.characterIconImageUrl,
                modifier = Modifier
                    .size(50.dp)
                    .clip(shape = CircleShape),
            )
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = uiState.characterDetailModel.characterName,
                        style = OffroadTheme.typography.subtitle2Bold,
                        color = Sub4,
                    )
                    if (uiState.characterDetailModel.isRepresentative) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_my_page_representative),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 6.dp)
                                .size(20.dp),
                        )
                    }
                }
                Text(
                    text = uiState.characterDetailModel.characterSummaryDescription,
                    style = OffroadTheme.typography.textContentsSmall,
                    color = Gray300,
                    modifier = Modifier.padding(top = 4.dp),
                )
            }
        }
        DottedHorizontalDivider(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
        )
    }
}

@Composable
private fun DottedHorizontalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = 1.dp,
    gapWidth: Dp = 4.dp,
    dotWidth: Dp = 4.dp,
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(thickness)
    ) {
        val stroke = Stroke(
            width = thickness.toPx(),
            pathEffect = PathEffect.dashPathEffect(
                floatArrayOf(dotWidth.toPx(), gapWidth.toPx())
            )
        )

        drawLine(
            color = Contents2,
            start = Offset(0f, size.height / 2),
            end = Offset(size.width, size.height / 2),
            strokeWidth = thickness.toPx(),
            pathEffect = stroke.pathEffect,
        )
    }
}

@Composable
private fun CharacterIntroductionItem(introduction: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Main1, shape = RoundedCornerShape(10.dp)
            )
            .padding(vertical = 20.dp, horizontal = 22.dp),
    ) {
        Text(
            text = introduction,
            style = OffroadTheme.typography.boxMedi,
            color = Gray400,
        )
    }
}