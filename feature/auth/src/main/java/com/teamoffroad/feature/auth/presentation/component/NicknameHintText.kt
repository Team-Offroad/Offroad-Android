package com.teamoffroad.feature.auth.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.teamoffroad.core.designsystem.theme.Gray400
import com.teamoffroad.core.designsystem.theme.OffroadTheme

@Composable
fun NicknameHintText(
    modifier: Modifier = Modifier,
    value: String = ""
) {
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = value,
            style = OffroadTheme.typography.hint,
            color = Gray400,
        )
    }
}