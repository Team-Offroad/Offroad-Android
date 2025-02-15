package com.teamoffroad.feature.mypage.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.SettingDiary
import com.teamoffroad.core.designsystem.theme.Sub4
import com.teamoffroad.offroad.feature.mypage.R

@Composable
fun UserDiary(
    modifier: Modifier = Modifier,
    navigateToUserDiary: () -> Unit
) {
    Row(
        modifier = modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .background(SettingDiary)
            .clickableWithoutRipple { navigateToUserDiary() }
            .fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp),
            text = "일기",
            style = OffroadTheme.typography.textBold,
            color = Sub4,
        )
        Image(
            modifier = Modifier
                .padding(start = 116.dp, end = 12.dp)
                .padding(top = 24.dp, bottom = 16.dp),
            painter = painterResource(id = R.drawable.ic_my_page_diary),
            contentDescription = "diary"
        )
    }
}