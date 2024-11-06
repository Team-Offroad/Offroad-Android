package com.teamoffroad.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.core.designsystem.theme.White25

@Composable
fun OffroadTagItem(
    text: String,
    textColor: Color = White,
    style: TextStyle = OffroadTheme.typography.subtitle2Semibold,
    backgroundColor: Color = White25,
    borderColor: Color = backgroundColor,
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        color = textColor,
        style = style,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(10.dp),
            )
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(10.dp),
                color = borderColor,
            )
            .padding(vertical = 12.dp)
    )
}