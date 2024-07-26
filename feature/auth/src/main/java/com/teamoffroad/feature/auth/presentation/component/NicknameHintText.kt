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

@Composable
fun NicknameHintText(
    modifier: Modifier = Modifier,
    text: String,
    isDuplicate: ValidateResult
) {


    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = when (isDuplicate) {
                ValidateResult.Duplicate -> "중복된 닉네임이에요. 다른 멋진 이름이 있으신가요?"
                else -> "*한글 2~8자, 영어 2~16자 이내로 작성해주세요."
            },
            style = OffroadTheme.typography.hint,
            color = if (isDuplicate == ValidateResult.Duplicate) {
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