package com.teamoffroad.feature.home.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.OffroadTagItem
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.offroad.feature.home.R

@Composable
fun EmblemNameText(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Box(
            modifier = modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            OffroadTagItem(
                text = "초보 모험가",
                textColor = White,
                style = OffroadTheme.typography.subtitle2Semibold,
                backgroundColor = Sub
            )
            Image(
                painter = painterResource(id = R.drawable.ic_home_change_title),
                contentDescription = "change title",
                modifier = Modifier.padding(end = 20.dp)
            )
        }
    }

}