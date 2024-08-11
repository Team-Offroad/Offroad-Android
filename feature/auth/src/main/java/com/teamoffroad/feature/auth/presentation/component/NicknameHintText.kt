package com.teamoffroad.feature.auth.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.teamoffroad.core.designsystem.theme.Error
import com.teamoffroad.core.designsystem.theme.Gray400
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.offroad.feature.auth.R

@Composable
fun NicknameHintText(
    text: String,
    isDuplicate: NicknameValidateResult
) {
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = when (isDuplicate) {
                NicknameValidateResult.Duplicate -> stringResource(R.string.auth_duplicated_nickname)
                NicknameValidateResult.NicknameValidateFailure -> stringResource(id = R.string.auth_invalid_nickname)
                NicknameValidateResult.Success -> "좋은 닉네임이에요"
                else -> stringResource(R.string.auth_empty_nickname)
            },
            style = OffroadTheme.typography.hint,
            color = when (isDuplicate) {
                NicknameValidateResult.Duplicate -> Error
                else -> checkNicknameHint(text)
            }
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
