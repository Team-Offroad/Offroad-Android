package com.teamoffroad.feature.home.presentation.component.quest.progressbar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Contents1GraphMain
import com.teamoffroad.core.designsystem.theme.Contents1GraphSub
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.PretendardBold
import com.teamoffroad.core.designsystem.theme.PretendardRegular
import com.teamoffroad.core.designsystem.theme.White25
import com.teamoffroad.feature.home.presentation.HomeViewModel
import com.teamoffroad.feature.home.presentation.model.HomeProgressBarModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CircleProgressBar(data: HomeProgressBarModel, viewModel: HomeViewModel) {
    val recentQuestProgress = viewModel.circleProgressBar.value

    Box(
        modifier = Modifier
            .padding(vertical = 14.dp, horizontal = 34.dp),
    ) {
        CircularProgressIndicator(
            progress = recentQuestProgress,
            color = Contents1GraphMain,
            trackColor = Contents1GraphSub,
            strokeWidth = 9.dp,
            strokeCap = StrokeCap.Round,
            modifier = Modifier
                .align(Alignment.Center)
                .heightIn(min = 82.dp)
                .aspectRatio(1f)
        )

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
                        color = White25,
                    )
                ) {
                    append(" / ")
                    append(data.total.toString())
                }
            },
            style = OffroadTheme.typography.bothRecentNumRegular,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}