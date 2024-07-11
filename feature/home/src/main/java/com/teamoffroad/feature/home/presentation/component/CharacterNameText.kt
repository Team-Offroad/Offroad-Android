package com.teamoffroad.feature.home.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Black15
import com.teamoffroad.core.designsystem.theme.CharacterName
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White

@Composable
fun CharacterNameText(name: String) {
    Text(
        style = OffroadTheme.typography.subtitle2Semibold,
        text = name,
        modifier = Modifier
            .padding(start = 24.dp, top = 12.dp)
            .background(
                color = CharacterName,
                shape = RoundedCornerShape(6.dp)
            )
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(6.dp),
                color = Black15
            )
            .padding(horizontal = 16.dp)
            .padding(vertical = 6.dp),
        color = White
    )
}