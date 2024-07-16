package com.teamoffroad.feature.auth.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.teamoffroad.core.designsystem.theme.Error
import com.teamoffroad.core.designsystem.theme.Gray400
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.auth.presentation.AuthState

@Composable
fun NicknameHintText(
    nicknameState: AuthState
) {
    val color: Color = when (nicknameState) {
        AuthState.Empty -> Gray400
        AuthState.Editing -> Gray400
        AuthState.InvalidateNickname -> Error
        else -> Gray400
    }

    val value = "*한글 2~8자, 영어 2~16자 이내로 작성해주세요."
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = value,
            style = OffroadTheme.typography.hint,
            color = color,
        )
    }
}