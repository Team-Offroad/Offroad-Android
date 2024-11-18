package com.teamoffroad.characterchat.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.characterchat.presentation.model.TimeType
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import java.util.Locale

@Composable
fun TimeLabel(
    modifier: Modifier = Modifier,
    time: Triple<TimeType, Int, Int>,
) {
    Text(
        text = "${time.first.krName} ${time.second}:${String.format(Locale.getDefault(), "%02d", time.third)}",
        color = Main3,
        style = OffroadTheme.typography.textContents,
        modifier = modifier
            .padding(horizontal = 6.dp),
    )
}
