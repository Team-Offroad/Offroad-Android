package com.teamoffroad.feature.mypage.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Gray400
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub2

@Composable
fun AnnouncementDetailHeader(
    year: String,
    month: String,
    day: String,
    text: String,
    isImportant: Boolean,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(top = 18.dp, bottom = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "$year / ", color = Gray400, style = OffroadTheme.typography.textContents)
            Text(text = "$month / ", color = Gray400, style = OffroadTheme.typography.textContents)
            Text(text = day, color = Gray400, style = OffroadTheme.typography.textContents)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (isImportant) {
                Text(
                    text = "[중요] ",
                    style = OffroadTheme.typography.tabBarMedi,
                    color = Sub2
                )
            }
            Text(text = text, color = Main2, style = OffroadTheme.typography.subtitle2Semibold)
        }
    }
}