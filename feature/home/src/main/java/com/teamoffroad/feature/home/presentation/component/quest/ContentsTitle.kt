package com.teamoffroad.feature.home.presentation.component.quest

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.OffroadTheme


@Composable
fun ContentsTitle(
    title: String,
    textColor: Color = Main1
) {
    Text(
        text = title,
        modifier = Modifier
            .padding(start = 14.dp),
        color = textColor,
        style = OffroadTheme.typography.textContents,
    )
}