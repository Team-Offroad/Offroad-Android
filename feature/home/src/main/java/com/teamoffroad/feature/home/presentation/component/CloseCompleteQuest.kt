package com.teamoffroad.feature.home.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.teamoffroad.core.common.component.ContentsLocation
import com.teamoffroad.core.common.component.ContentsTitle
import com.teamoffroad.core.designsystem.theme.Black25
import com.teamoffroad.core.designsystem.theme.Contents2
import com.teamoffroad.core.designsystem.theme.OpticianSansRegular
import com.teamoffroad.core.designsystem.theme.Sub4
import com.teamoffroad.feature.home.HomeProgressBarData
import com.teamoffroad.offroad.feature.home.R

@Composable
fun CloseCompleteRequest(modifier: Modifier = Modifier, data: HomeProgressBarData) {
    Surface(
        color = Contents2,
        modifier = modifier
            .clip(shape = RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row {
                ContentsTitle(title = data.title)
                Spacer(modifier = Modifier.padding(start = 4.dp))
                Image(
                    painter = painterResource(id = R.drawable.img_home_close_complete),
                    contentDescription = "recent quest",
                    modifier = Modifier
                        .padding(top = 16.dp)
                )
            }
            Spacer(modifier = Modifier.padding(top = 16.dp))
            LinearProgressBar(data)
            Spacer(modifier = Modifier.padding(top = 20.dp))
            ContentsLocation(data.location)
            Spacer(modifier = Modifier.padding(bottom = 12.dp))
        }
    }

}

@Composable
private fun LinearProgressBar(data: HomeProgressBarData) {
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