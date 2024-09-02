package com.teamoffroad.feature.mypage.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub2
import com.teamoffroad.offroad.feature.mypage.R

@Composable
fun GainedEmblemsHeader() {
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(top = 42.dp, bottom = 24.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "획득 칭호",
                style = OffroadTheme.typography.title,
                color = Main2,
            )
            Image(
                painter = painterResource(id = R.drawable.ic_my_page_emblems_tag),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .width(26.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_my_page_check_circle),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = "퀘스트를 달성하고 보상으로 칭호를 얻어보아요!",
                style = OffroadTheme.typography.boxMedi,
                color = Sub2,
                modifier = Modifier.padding(start = 6.dp),
            )
        }
    }
}