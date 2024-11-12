package com.teamoffroad.characterchat.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme

@Composable
fun TimeLabel(
    modifier: Modifier = Modifier,
) {
    Text(
        text = "오전 10:00",
        color = Main3,
        style = OffroadTheme.typography.textContents,
        modifier = modifier
            .padding(horizontal = 6.dp)
    )
}

@Preview
@Composable
fun TimeLabelPreview() {
    TimeLabel()
}