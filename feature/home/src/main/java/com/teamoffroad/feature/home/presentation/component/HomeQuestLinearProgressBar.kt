package com.teamoffroad.feature.home.presentation.component

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.PretendardBold
import com.teamoffroad.core.designsystem.theme.PretendardRegular
import com.teamoffroad.core.designsystem.theme.White25
import com.teamoffroad.feature.home.presentation.HomeViewModel
import com.teamoffroad.feature.home.presentation.model.HomeProgressBarModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun LinearProgressBar(data: HomeProgressBarModel, viewModel: HomeViewModel) {
    val closeCompleteQuestProgress = viewModel.linearProgressBar.value

    Box(
        modifier = Modifier
            .padding(top = 30.dp),
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        fontFamily = PretendardBold,
                        fontWeight = FontWeight.Bold,
                        color = Main1,
                    )
                ) {
                    append(data.amount.toString())
                }
                withStyle(
                    SpanStyle(
                        fontFamily = PretendardRegular,
                        fontWeight = FontWeight.Normal,
                        color = White25
                    )
                ) {
                    append(" / ")
                    append(data.total.toString())
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
            style = OffroadTheme.typography.bothUpcomingNumRegular,
        )

        ConstraintLayout(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 62.dp)
        ) {
            val progress = createRef()
            CustomLinearProgressBar(
                progress = closeCompleteQuestProgress,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .constrainAs(progress) {
                        start.linkTo(parent.start, margin = (-6).dp)
                    }
            )
        }
    }
}

@Composable
fun CustomLinearProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    backgroundColor: Color = White25,
    progressColor: Color = Main1,
    height: Dp = 8.dp
) {
    val cornerRadius = height / 2
    val coercedProgress = progress.coerceIn(0f, 1f)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = cornerRadius,
                        bottomStart = 0.dp,
                        bottomEnd = cornerRadius
                    )
                )
        )

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(coercedProgress)
                .background(
                    color = progressColor,
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = cornerRadius,
                        bottomStart = 0.dp,
                        bottomEnd = cornerRadius
                    )
                )
        )
    }
}
