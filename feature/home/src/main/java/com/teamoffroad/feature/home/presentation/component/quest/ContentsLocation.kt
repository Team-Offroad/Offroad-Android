package com.teamoffroad.feature.home.presentation.component.quest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White25

@Composable
fun ContentsLocation(
    location: String,
    textColor: Color = Main1
) {
    Text(
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .background(
                color = White25,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(horizontal = 30.dp, vertical = 6.dp),
        text = location,
        style = OffroadTheme.typography.textContentsSmall,
        color = textColor,
    )
}
