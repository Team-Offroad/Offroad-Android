package com.teamoffroad.feature.auth.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme

@Composable
fun AgreeTermsAndConditionsTopBar(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(horizontal = 24.dp)) {
        Text(
            text = "약관 동의",
            color = Main2,
            style = OffroadTheme.typography.title,
            modifier = Modifier
                .padding(top = 158.dp)
                .padding(bottom = 12.dp)
        )
        Text(
            text = "필수항목 및 선택항목 약관에 동의해주세요.",
            color = Main2,
            style = OffroadTheme.typography.textAuto
        )
    }
}