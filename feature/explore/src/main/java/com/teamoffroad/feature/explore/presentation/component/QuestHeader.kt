package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.offroad.feature.explore.R

@Composable
fun QuestHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 28.dp, bottom = 20.dp)
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = stringResource(R.string.explore_quest_list),
            style = OffroadTheme.typography.title,
        )
        Image(
            painter = painterResource(id = R.drawable.ic_explore_scroll),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 8.dp)
                .padding(vertical = 10.dp)
                .size(24.dp),
        )
    }
    HorizontalDivider(
        color = Gray100,
    )
}