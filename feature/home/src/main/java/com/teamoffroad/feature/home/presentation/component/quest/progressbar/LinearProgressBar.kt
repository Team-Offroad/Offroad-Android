package com.teamoffroad.feature.home.presentation.component.quest.progressbar

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.teamoffroad.core.designsystem.theme.Black25
import com.teamoffroad.core.designsystem.theme.OpticianSansRegular
import com.teamoffroad.core.designsystem.theme.Sub4
import com.teamoffroad.feature.home.presentation.model.HomeProgressBarModel

@Composable
fun LinearProgressBar(data: HomeProgressBarModel) {
    ConstraintLayout {
        val text = createRef()
        Text(
            textAlign = TextAlign.Center,
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        fontFamily = OpticianSansRegular,
                        fontWeight = FontWeight.Normal,
                        color = Sub4,
                        fontSize = 62.sp
                    )
                ) {
                    append(data.amount.toString())
                }
                withStyle(
                    SpanStyle(
                        fontFamily = OpticianSansRegular,
                        fontWeight = FontWeight.Normal,
                        fontSize = 30.sp,
                        color = Black25
                    )
                ) {
                    append("/")
                    append(data.total.toString())
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(text) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
    Spacer(modifier = Modifier.padding(top = 8.dp))
    ConstraintLayout {
        val progress = createRef()
        var closeCompleteProgress by remember { mutableStateOf(0.8f) }

        LinearProgressIndicator(
            progress = { closeCompleteProgress },
            trackColor = Black25,
            color = Sub4,
            strokeCap = StrokeCap.Round,
            modifier = Modifier
                .height(8.dp)
                .padding(end = 10.dp)
                .constrainAs(progress) {
                    start.linkTo(parent.start, margin = (-3).dp)
                }
        )
    }
}