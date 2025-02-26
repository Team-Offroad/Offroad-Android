package com.teamoffroad.feature.diary.component

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Stroke
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.diary.presentation.model.MemoryLight
import com.teamoffroad.offroad.feature.diary.R

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun MemoryLightScreen(
    modifier: Modifier = Modifier,
    memoryLightList: List<MemoryLight>,
    onCancelClick: (String?) -> Unit,
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 2 })
    BackHandler {
        onCancelClick(null)
    }
    Column(
        modifier = modifier
            .clickableWithoutRipple(
                interactionSource = MutableInteractionSource()
            ) {}
            //TODO. 배경그라디언트 적용하기
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF70DAFF),
                        Color(0xFF5580FF)
                    )
                )
            )
    ) {
        Image(
            modifier = Modifier
                .padding(top = 65.dp, bottom = 48.dp)
                .padding(end = 20.dp)
                .align(Alignment.End)
                .clickableWithoutRipple { onCancelClick(null) },
            painter = painterResource(id = R.drawable.ic_diary_dialog_close),
            contentDescription = "close"
        )
        HorizontalPager(
            modifier = Modifier.padding(bottom = 32.dp),
            state = pagerState
        ) { page ->
            MemoryLightItems()
        }
        Row(
            modifier = Modifier.padding(bottom = 64.dp)
        ) {

        }
    }
}

@Composable
private fun MemoryLightItems(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
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
                modifier = Modifier
                    .padding(bottom = 28.dp),
                text = "2025년 2월 11일\n오늘의 기억빛",
                color = White,
                style = OffroadTheme.typography.subtitle2Semibold
            )
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = "오늘의 기억을 AI가 한 줄로 요약합니다.",
                color = Main2,
                style = OffroadTheme.typography.textBold
            )
            Text(
                modifier = Modifier.padding(bottom = 32.dp),
                text = "그리고 오늘의 기억을 오늘 하루동안 나눈 대화, 방문한 장소, " +
                        "시간 데이터를 바탕으로 요약합니다. 이때 단순 요약이 아니라 AI가 남기는" +
                        " 일종의 메시지 형태라고 보시면 될 것 같고 앞으로에 대한 기대, 응원," +
                        " 위로 등의 내용이 담겨 있습니다. 앞으로에 대한 기대, 응원, 위로 등의" +
                        " 내용이 담겨 있습니다. 내용이 담겨 있습니다. 앞으로에 대한 기대, 응원," +
                        " 위로 등의 내용이 담겨 있습니다.",
                color = Main2,
                style = OffroadTheme.typography.boxMedi.copy(
                    lineHeight = 20.sp
                )
            )
            DottedHorizontalDivider()
            Text(
                modifier = Modifier.padding(bottom = 12.dp),
                text = "오늘의 추천",
                color = Sub,
                style = OffroadTheme.typography.textContents
            )
            Box(
                modifier = Modifier
                    .padding(bottom = 34.dp)
                    .background(
                        color = Main1,
                        shape = RoundedCornerShape(9.dp)
                    )
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.padding(end = 2.dp),
                        painter = painterResource(id = R.drawable.ic_diary_memory_light_today_recommend),
                        contentDescription = "today_recommend"
                    )
                    Text(
                        modifier = Modifier.padding(PaddingValues(vertical = 15.dp)),
                        text = "내일 묵은지 돼지갈비 왕목살 세트 어때요?",
                        color = Main2,
                        style = OffroadTheme.typography.marketing,
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
