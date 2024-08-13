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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.offroad.feature.mypage.R

@Composable
fun GainedCharacterHeader() {
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(top = 42.dp, bottom = 24.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.my_page_gained_character),
                style = OffroadTheme.typography.title,
                color = Main2,
            )
            Image(
                painter = painterResource(id = R.drawable.ic_my_page_character),
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
                text = stringResource(R.string.my_page_gained_character_hint),
                style = OffroadTheme.typography.boxMedi,
                color = Main2,
                modifier = Modifier.padding(start = 6.dp),
            )
        }
    }
}