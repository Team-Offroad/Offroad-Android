package com.teamoffroad.core.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.core.designsystem.theme.White

@Composable
fun PopupTagActive(text: String) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        color = White,
        style = OffroadTheme.typography.subtitle2Semibold,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Sub,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(vertical = 8.dp)
    )
}

@Composable
fun PopupTagInactive() {

}