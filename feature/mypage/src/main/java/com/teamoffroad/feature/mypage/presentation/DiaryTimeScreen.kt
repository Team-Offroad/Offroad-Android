package com.teamoffroad.feature.mypage.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.actionBarPadding
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub2
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.mypage.presentation.component.DiaryTimePicker
import com.teamoffroad.feature.mypage.presentation.component.SettingHeader
import com.teamoffroad.offroad.feature.mypage.R

@Composable
fun DiaryTimeScreen(
    navigateToBack: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Main1)
            .navigationPadding()
            .actionBarPadding(),
    ) {
        NavigateBackAppBar(
            text = stringResource(R.string.my_page_settings),
            modifier = Modifier.padding(top = 20.dp)
        ) {
            navigateToBack()
        }
        SettingHeader(
            text = stringResource(R.string.my_page_setting_item_diary_time),
            painterResources = R.drawable.ic_my_page_diary_time,
        )
        HorizontalDivider(
            color = Gray100,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
        )
        Image(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            painter = painterResource(id = R.drawable.img_date_time_dummy),
            contentDescription = "diary_time"
        )
        Text(
            modifier = Modifier
                .padding(bottom = 28.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.my_page_setting_diary_time_when_receive),
            color = Main2,
        )
        DiaryTimePicker(
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        Row(
            modifier = Modifier
                .padding(top = 54.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_my_page_check_circle),
                contentDescription = "diary_time_notice"
            )
            Text(
                text = stringResource(id = R.string.my_page_setting_diary_time_notice_first),
                style = OffroadTheme.typography.boxMedi,
                color = Sub2,
            )
        }
        Text(
            modifier = Modifier
                .padding(bottom = 34.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.my_page_setting_diary_time_notice_second),
            style = OffroadTheme.typography.boxMedi,
            color = Sub2,
        )
        DiaryTimeButton(
            modifier = Modifier
                .padding(bottom = 24.dp)
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .clickableWithoutRipple {
                    Log.d("asdasd", "asdasd")
                },
            text = stringResource(id = R.string.my_page_setting_diary_success),
            textColor = White,
            backgroundColor = Main2,
        )
    }
}

@Composable
private fun DiaryTimeButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color,
    backgroundColor: Color,
) {
    Text(
        text = text,
        color = textColor,
        style = OffroadTheme.typography.btnSmall,
        textAlign = TextAlign.Center,
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(5.dp),
            )
            .border(
                width = 1.dp, shape = RoundedCornerShape(5.dp),
                color = Main2,
            )
            .padding(vertical = 14.dp)
    )
}