package com.teamoffroad.characterchat.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub4

@Composable
fun CharacterChatBox() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        ChatTextBox {
            Text(
                text = "오브",
                style = OffroadTheme.typography.textBold,
                color = Sub4,
            )
            Text(
                text = ": ",
                color = Main2,
                style = OffroadTheme.typography.textRegular,
                modifier = Modifier.padding(start = 4.dp),
            )
            Text(
                text = "오.. 맛있었겠네. 어차피 난 인간아라 이아니라서가나다라마바사아자아자",
                style = OffroadTheme.typography.textRegular,
                color = Main2,
            )
        }
        TimeLabel(
            modifier = Modifier.align(Alignment.Bottom),
        )
    }
}

@Preview
@Composable
fun CharacterChatBoxPreview() {
    CharacterChatBox()
}
