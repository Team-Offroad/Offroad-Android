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
import androidx.compose.runtime.LaunchedEffect
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
    firstPage: Int = 0,
    secondPage: Int = 1,
    onCancelClick: (Boolean) -> Unit,
    updateTimeSettingDialogState: (Boolean) -> Unit,
    patchDiaryTutorialChecked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = firstPage, pageCount = { secondPage + 1 })

    LaunchedEffect(Unit) {
        patchDiaryTutorialChecked()
    }

    BackHandler(enabled = pagerState.currentPage == firstPage || pagerState.currentPage == secondPage) {
        coroutineScope.launch {
            when (pagerState.currentPage) {
                firstPage -> {
                    onCancelClick(false)
                    updateTimeSettingDialogState(true)
                }

                secondPage -> pagerState.animateScrollToPage(
                    pagerState.currentPage - secondPage,
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
            painter = painterResource(id = R.drawable.ic_diary_dialog_close),
            contentDescription = "close",
            modifier = Modifier
                .padding(top = 65.dp, bottom = 40.dp)
                .padding(end = 20.dp)
                .align(Alignment.End)
                .clickableWithoutRipple { onCancelClick(false) },
        )
        HorizontalPager(
            verticalAlignment = Alignment.Top,
            state = pagerState,
            userScrollEnabled = false,
            modifier = Modifier.weight(1f),
        ) { page ->
            when (page) {
                firstPage -> DiaryHintFirstScreen()
                secondPage -> DiaryHintSecondScreen()
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 52.dp, bottom = 77.dp),
        ) {
            DiaryHintProgressIndicator(animateActive = pagerState.currentPage >= firstPage)
            Spacer(modifier = Modifier.width(7.dp))
            DiaryHintProgressIndicator(animateActive = pagerState.currentPage >= secondPage)

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
                            pagerState.animateScrollToPage(pagerState.currentPage + secondPage)
                        }

                        1 -> {
                            updateTimeSettingDialogState(true)
                            onCancelClick(false)
                        }
                    }
                },
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(id = R.string.diary_dialog_next),
                color = White,
                style = OffroadTheme.typography.textRegular,
                modifier = Modifier.padding(vertical = 14.dp),
            )
        }
    }
}