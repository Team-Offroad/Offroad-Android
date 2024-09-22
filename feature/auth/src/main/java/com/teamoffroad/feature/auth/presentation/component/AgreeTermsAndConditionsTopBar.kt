package com.teamoffroad.feature.auth.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.offroad.feature.auth.R

@Composable
fun AgreeTermsAndConditionsTopBar(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(horizontal = 24.dp)) {
        Text(
            text = stringResource(R.string.auth_agree_and_terms_conditions_title),
            color = Main2,
            style = OffroadTheme.typography.title,
            modifier = Modifier
                .padding(top = 158.dp)
                .padding(bottom = 12.dp)
        )
        Text(
            text = stringResource(R.string.auth_agree_and_terms_conditions_sub_title),
            color = Main2,
            style = OffroadTheme.typography.textAuto
        )
    }
}