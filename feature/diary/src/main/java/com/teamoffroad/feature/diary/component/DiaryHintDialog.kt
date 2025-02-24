package com.teamoffroad.feature.diary.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Black
import com.teamoffroad.core.designsystem.theme.DiaryProgressBar
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.offroad.feature.diary.R

@Composable
fun DiaryHintDialog(
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit,
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 2 })

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Black.copy(alpha = 0.8f))
    ) {
        Image(
            modifier = Modifier
                .padding(top = 65.dp, bottom = 40.dp)
                .padding(end = 32.dp)
                .align(Alignment.End),
            painter = painterResource(id = R.drawable.ic_diary_dialog_close),
            contentDescription = "close"
        )
        HorizontalPager(
            modifier = Modifier.weight(1f),
            state = pagerState,
            userScrollEnabled = false,
        ) { page ->
            when (page) {
                0 -> {
                    Image(painter = painterResource(id = R.drawable.img_diary_empty), contentDescription = "dummy")
                }
                1 -> {}
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 52.dp, bottom = 77.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Box(
                modifier = Modifier
                    .background(color = DiaryProgressBar, shape = RoundedCornerShape(14.dp))
                    .width(17.dp)
                    .height(4.dp)
            )
            Spacer(modifier = Modifier.width(7.dp))
            Box(
                modifier = Modifier
                    .background(color = DiaryProgressBar, shape = RoundedCornerShape(14.dp))
                    .width(17.dp)
                    .height(4.dp)
            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 25.dp)
                .background(color = Sub, shape = RoundedCornerShape(5.dp))
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.padding(vertical = 14.dp),
                text = stringResource(id = R.string.diary_dialog_next),
                color = White,
                style = OffroadTheme.typography.textRegular,
            )

        }


    }
}