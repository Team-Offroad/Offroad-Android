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
) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        TimeLabel(
            modifier = Modifier.align(Alignment.Bottom),
        )
        ChatTextBox {
            Text(
                text = "오.. 맛있었겠네. 어차피 난 인간아라 이아니라서가나다라마바사아자아자",
                style = OffroadTheme.typography.textRegular,
                color = Main2,
            )
        }
    }
}

@Preview
@Composable
fun UserChatBoxPreview() {
    UserChatBox()
}