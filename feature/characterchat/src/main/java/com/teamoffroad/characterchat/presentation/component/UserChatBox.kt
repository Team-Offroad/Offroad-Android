package com.teamoffroad.characterchat.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme

@Composable
fun UserChatBox(
    text: String,
    time: String,
) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        TimeLabel(
            modifier = Modifier.align(Alignment.Bottom),
            time = time,
        )
        ChatTextBox {
            Text(
                text = text,
                style = OffroadTheme.typography.textRegular,
                color = Main2,
            )
        }
    }
}
