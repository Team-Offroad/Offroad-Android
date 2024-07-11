package com.teamoffroad.feature.home.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.OffroadTheme


@Composable
fun ContentsTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .padding(start = 12.dp, top = 16.dp),
        color = Main1,
        style = OffroadTheme.typography.textContents,
    )
}