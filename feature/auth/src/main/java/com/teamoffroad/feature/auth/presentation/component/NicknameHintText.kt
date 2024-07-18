package com.teamoffroad.feature.auth.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.teamoffroad.core.designsystem.theme.Error
import com.teamoffroad.core.designsystem.theme.Gray400
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.auth.presentation.model.NicknameUiState

@Composable
fun NicknameHintText(
    modifier: Modifier = Modifier,
    value: String = "*한글 2~8자, 영어 2~16자 이내로 작성해주세요.",
    text: String,
    isDuplicate: NicknameUiState
) {
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = value,
            style = OffroadTheme.typography.hint,
            color = if (isDuplicate==NicknameUiState.Duplicated) {
                Error
            } else {
                checkNicknameHint(text)
            },
        )
    }
}

fun checkNicknameHint(text: String): Color {
    if (text.isEmpty()) return Gray400

    val koreanRegex = Regex("^[가-힣]*$")
    val englishRegex = Regex("^[a-zA-Z]*$")

    return when {
        koreanRegex.matches(text) && text.length in 2..8 -> Gray400
        englishRegex.matches(text) && text.length in 2..16 -> Gray400
        else -> Error
    }
}