package com.teamoffroad.feature.diary.component

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.Black
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.offroad.feature.diary.R
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun DiaryHintDialog(
    modifier: Modifier = Modifier,
    firstPage: Int = 0,
    secondPage: Int = 1,
    onCancelClick: (Boolean) -> Unit,
    updateTimeSettingDialogState: (Boolean) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = firstPage, pageCount = { secondPage + 1 })

    BackHandler(enabled = pagerState.currentPage == firstPage || pagerState.currentPage == secondPage) {
        coroutineScope.launch {
            when (pagerState.currentPage) {
                0 -> onCancelClick(false)
                1 -> pagerState.animateScrollToPage(
                    pagerState.currentPage - 1,
                )
            }
        }
    }

    Column(
        modifier = modifier
            .clickableWithoutRipple(
                interactionSource = MutableInteractionSource()
            ) {}
            .fillMaxSize()
            .background(Black.copy(alpha = 0.8f))
    ) {
        Image(
            modifier = Modifier
                .padding(top = 65.dp, bottom = 40.dp)
                .padding(end = 20.dp)
                .align(Alignment.End)
                .clickableWithoutRipple { onCancelClick(false) },
            painter = painterResource(id = R.drawable.ic_diary_dialog_close),
            contentDescription = "close"
        )
        HorizontalPager(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.Top,
            state = pagerState,
            userScrollEnabled = false,
        ) { page ->
            when (page) {
                0 -> DiaryHintFirstScreen()
                1 -> DiaryHintSecondScreen()
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 52.dp, bottom = 77.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            DiaryHintProgressIndicator(animateActive = pagerState.currentPage >= 0)
            Spacer(modifier = Modifier.width(7.dp))
            DiaryHintProgressIndicator(animateActive = pagerState.currentPage >= 1)

        }
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 25.dp)
                .background(color = Sub, shape = RoundedCornerShape(5.dp))
                .fillMaxWidth()
                .clickableWithoutRipple {
                    when (pagerState.currentPage) {
                        0 -> coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }

                        1 -> {
                            updateTimeSettingDialogState(true)
                            onCancelClick(false)
                            //TODO. 튜토리얼완료 api 쏘기
                        }
                    }
                },
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