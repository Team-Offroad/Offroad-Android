package com.teamoffroad.feature.diary.component

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Stroke
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.diary.domain.model.MemoryLight
import com.teamoffroad.feature.diary.domain.model.MemoryLightSetting
import com.teamoffroad.offroad.feature.diary.R

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun MemoryLightScreen(
    memoryLight: MemoryLightSetting,
    onCancelClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(
        initialPage = memoryLight.initialPage,
        pageCount = { memoryLight.pageCount })

    BackHandler {
        onCancelClick(false)
    }
    Column(
        modifier = modifier
            .clickableWithoutRipple(
                interactionSource = MutableInteractionSource()
            ) {}
            .drawBehind {
                drawRect(
                    brush = Brush.radialGradient(
                        colors = listOf(Color(0xFF70DAFF), Color(0xFF5580FF)),
                        center = Offset(0f, 0f),
                        radius = size.minDimension
                    ),
                    size = size
                )
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_diary_dialog_close),
            contentDescription = "close",
            modifier = Modifier
                .padding(top = 65.dp, bottom = 30.dp)
                .padding(end = 20.dp)
                .align(Alignment.End)
                .clickableWithoutRipple { onCancelClick(false) },
        )
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.padding(bottom = 28.dp),
        ) { page ->
            MemoryLightItems(
                memoryLight = memoryLight.memoryLight[page]
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clickableWithoutRipple {
                },
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_diary_memory_light_share),
                contentDescription = "share",
                modifier = Modifier.padding(end = 10.dp),
            )
            Text(
                text = stringResource(id = R.string.diary_memory_light_share),
                color = White,
                style = OffroadTheme.typography.textRegular,
            )
        }
    }
}

@Composable
private fun MemoryLightItems(
    memoryLight: MemoryLight,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .height(515.dp)
            .padding(horizontal = 24.dp)
            .background(color = White, shape = RoundedCornerShape(20.dp))
            .fillMaxWidth()
    ) {
        GradientCircle()
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(top = 102.dp)
        ) {
            Text(
                text = stringResource(
                    id = R.string.diary_memory_light_title,
                    memoryLight.year,
                    memoryLight.month,
                    memoryLight.day,
                ),
                color = White,
                style = OffroadTheme.typography.subtitle2Semibold,
                modifier = Modifier
                    .padding(bottom = 28.dp),
            )
            Text(
                text = memoryLight.summation,
                color = Main2,
                style = OffroadTheme.typography.textBold,
                modifier = Modifier.padding(bottom = 16.dp),
            )
            Text(
                text = memoryLight.content,
                color = Main2,
                style = OffroadTheme.typography.boxMedi.copy(
                    lineHeight = 20.sp
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 32.dp),
            )
            DottedHorizontalDivider()
            Text(
                text = stringResource(id = R.string.diary_memory_light_today_recommend),
                color = Sub,
                style = OffroadTheme.typography.textContents,
                modifier = Modifier.padding(bottom = 12.dp),
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(bottom = 34.dp)
                    .background(
                        color = Main1,
                        shape = RoundedCornerShape(9.dp)
                    )
                    .fillMaxWidth(),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_diary_memory_light_today_recommend),
                        contentDescription = "today_recommend",
                        modifier = Modifier.padding(end = 2.dp),
                    )
                    Text(
                        text = memoryLight.dailyRecommend,
                        color = Main2,
                        style = OffroadTheme.typography.marketing,
                        modifier = Modifier.padding(PaddingValues(vertical = 15.dp)),
                    )
                }
            }
        }
    }
}

@Composable
fun DottedHorizontalDivider(
    color: Color = Stroke,
    strokeWidth: Float = 5f,
    dashWidth: Float = 10f,
    gapWidth: Float = 10f
) {
    Canvas(
        modifier = Modifier
            .padding(bottom = 32.dp)
            .fillMaxWidth()
            .height(1.dp)
    ) {
        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashWidth, gapWidth), 0f)
        drawLine(
            color = color,
            start = Offset(0f, size.height / 2),
            end = Offset(size.width, size.height / 2),
            strokeWidth = strokeWidth,
            pathEffect = pathEffect
        )
    }
}

@Composable
fun GradientCircle() {
    Box(
        modifier = Modifier
            .padding(top = 20.dp)
            .size(230.dp)
            .graphicsLayer {
                alpha = 0.99f
                renderEffect = BlurEffect(
                    radiusX = 80f,
                    radiusY = 80f,
                    edgeTreatment = TileMode.Decal
                )
            }
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF70DAFF).copy(0.8f),
                        Color(0xFF5580FF).copy(0.6f),
                        Color.Transparent
                    ),
                    center = Offset(center.x - 90f, center.y - 100f),
                    radius = size.minDimension / 1.5f
                )
            )
        }
    }
}