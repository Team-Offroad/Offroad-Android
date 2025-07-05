package com.teamoffroad.characterchat.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.teamoffroad.characterchat.presentation.model.TimeType
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import java.util.Locale

@Composable
fun TimeLabel(
    time: Triple<TimeType, Int, Int>,
    textStyle: TextStyle = OffroadTheme.typography.textContents,
    textColor: Color = Main3,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "${time.first.krName} ${time.second}:${String.format(Locale.getDefault(), "%02d", time.third)}",
        color = textColor,
        style = textStyle,
        modifier = modifier
            .padding(horizontal = 6.dp),
    )
}
