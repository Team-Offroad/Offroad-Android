package com.teamoffroad.feature.auth.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.teamoffroad.core.designsystem.theme.ErrorNew
import com.teamoffroad.core.designsystem.theme.Gray400
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.auth.presentation.model.NicknameValidateResult
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
                NicknameValidateResult.Success -> "좋은 닉네임이에요!"
                else -> stringResource(R.string.auth_empty_nickname)
            },
            style = OffroadTheme.typography.hint,
            color = when (isDuplicate) {
                NicknameValidateResult.Duplicate -> ErrorNew
                else -> checkNicknameHint(text)
            }
        )
    }
}

fun checkNicknameHint(text: String): Color {
    if (text.isEmpty()) return Gray400
    val koreanRegex = Regex("[가-힣]")
    val englishOrDigitRegex = Regex("[a-zA-Z0-9]")

    var totalLength = 0
    var containsKorean = false
    var containsEnglishOrDigit = false
    val consonantRegex = Regex("[ㄱ-ㅎ]")

    for (char in text) {
        totalLength += when {
            koreanRegex.matches(char.toString()) -> {
                containsKorean = true
                2
            }

            englishOrDigitRegex.matches(char.toString()) -> {
                containsEnglishOrDigit = true
                1
            }

            else -> 0
        }

        if (consonantRegex.matches(char.toString())) {
            return ErrorNew
        }

        if (totalLength > 16) return ErrorNew
    }

    return when {
        containsKorean && !containsEnglishOrDigit -> if (totalLength in 4..16) Gray400 else ErrorNew
        containsEnglishOrDigit && !containsKorean -> if (totalLength in 2..16) Gray400 else ErrorNew
        else -> if (totalLength in 2..16) Gray400 else ErrorNew
    }
}
