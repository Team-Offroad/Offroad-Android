package com.teamoffroad.feature.mypage.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme

@Composable
fun SettingHeader(
    text: String,
    painterResources: Int,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(top = 42.dp, bottom = 24.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = text,
                style = OffroadTheme.typography.title,
                color = Main2,
            )
            Image(
                painter = painterResource(id = painterResources),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .width(26.dp)
            )
        }
    }
}