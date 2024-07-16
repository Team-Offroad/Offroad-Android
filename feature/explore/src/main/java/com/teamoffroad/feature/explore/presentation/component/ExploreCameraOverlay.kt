package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teamoffroad.core.designsystem.theme.Black
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.offroad.feature.explore.R

@Composable
fun ExploreCameraOverlay() {
    val annotatedHintString = buildAnnotatedString {
        withStyle(style = OffroadTheme.typography.textBold.toSpanStyle()) {
            append(stringResource(R.string.explore_camera_hint_first))
        }
        append(stringResource(R.string.explore_camera_hint_second))
    }
    Spacer(
        modifier = Modifier
            .height(16.dp)
            .fillMaxWidth()
            .background(Black.copy(alpha = 0.44f))
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(312.dp)
    ) {
        Spacer(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(Black.copy(alpha = 0.44f))
        )
        Image(
            painter = painterResource(id = R.drawable.bg_explore_camera_frame),
            contentDescription = "QR 코드 프레임",
            modifier = Modifier
                .size(312.dp)
        )
        Spacer(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(Black.copy(alpha = 0.44f))
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black.copy(alpha = 0.44f))
            .padding(top = 40.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_explore_qr),
            contentDescription = "QR 코드 스캔 아이콘",
            modifier = Modifier
                .size(28.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(22.dp))
        Text(
            text = annotatedHintString,
            style = OffroadTheme.typography.textRegular,
            color = White,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
    }
}
