package com.teamoffroad.characterchat.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.core.designsystem.theme.White25

@Composable
fun ChatButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Text(
        text = "채팅하기",
        color = White,
        modifier = modifier
            .background(color = White25, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 18.dp, vertical = 7.dp)
            .wrapContentSize()
            .clickable { onClick() },
    )
}

@Preview
@Composable
fun ChatButtonPreview() {
    ChatButton()
}
