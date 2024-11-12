package com.teamoffroad.feature.auth.presentation.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.teamoffroad.core.designsystem.theme.ErrorNew
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.auth.presentation.model.SetBirthDateUiState

@Composable
fun BirthDateHintText(
    modifier: Modifier = Modifier,
    value: String = "다시 한 번 확인해주세요.",
    isVisible: Boolean
) {
    Text(
        text = value,
        modifier = modifier.alpha(
            if (isVisible) 1f else 0f
        ),
        style = OffroadTheme.typography.hint,
        color = ErrorNew
    )

}