package com.teamoffroad.characterchat.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.BtnInactive
import com.teamoffroad.core.designsystem.theme.Main3

@Composable
fun ChatTextBox(
    content: @Composable () -> Unit,
) {
    Row(
        modifier = Modifier
            .widthIn(max = 262.dp)
            .wrapContentWidth()
            .border(1.dp, BtnInactive, shape = RoundedCornerShape(12.dp))
            .background(color = Main3, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 18.dp, vertical = 12.dp),
    ) {
        content()
    }
}
