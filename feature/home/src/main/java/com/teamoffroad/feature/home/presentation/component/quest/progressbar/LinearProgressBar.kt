package com.teamoffroad.feature.home.presentation.component.quest.progressbar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.teamoffroad.core.designsystem.theme.Main1
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
        modifier = Modifier.fillMaxSize().padding(top = 30.dp)
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        fontFamily = PretendardBold,
                        fontWeight = FontWeight.Bold,
                        color = Main1,
                        fontSize = with(LocalDensity.current) { 28.dp.toSp() }
                    )
                ) {
                    append(data.amount.toString())
                }
                withStyle(
                    SpanStyle(
                        fontFamily = PretendardRegular,
                        fontWeight = FontWeight.Normal,
                        fontSize = with(LocalDensity.current) { 28.dp.toSp() },
                        color = White25
                    )
                ) {
                    append(" / ")
                    append(data.total.toString())
                }
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopEnd)
                .padding(top = 62.dp)
        ) {
            val progress = createRef()
            LinearProgressIndicator(
                progress = closeCompleteQuestProgress,
                trackColor = White25,
                color = Main1,
                strokeCap = StrokeCap.Round,
                modifier = Modifier
                    .height(8.dp)
                    .padding(end = 10.dp)
                    .constrainAs(progress) {
                        start.linkTo(parent.start, margin = (-6).dp)
                    }
            )
        }
    }
}