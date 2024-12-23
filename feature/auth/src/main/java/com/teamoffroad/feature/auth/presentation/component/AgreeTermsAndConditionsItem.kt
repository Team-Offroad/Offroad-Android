package com.teamoffroad.feature.auth.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.Gray300
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub2
import com.teamoffroad.offroad.feature.auth.R

@Composable
fun AgreeTermsAndConditionsItem(
    text: String,
    isChecked: Boolean,
    isRequired: Boolean,
    modifier: Modifier = Modifier,
    dialogShown: () -> Unit,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 44.dp)
            .padding(bottom = 18.dp)
            .clickableWithoutRipple { dialogShown() },
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = when (isChecked) {
                    true -> painterResource(R.drawable.ic_agree_check_fill)
                    false -> painterResource(R.drawable.ic_agree_check_empty)
                },
                contentDescription = "check",
                modifier = Modifier
                    .padding(end = 12.dp)
                    .clickableWithoutRipple { onClick() }
            )
        }
        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = if (isRequired) Sub2 else Gray300,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(
                text = when (isRequired) {
                    true -> stringResource(R.string.auth_agree_and_terms_conditions_item_required)
                    false -> stringResource(R.string.auth_agree_and_terms_conditions_item_optional)
                },
                color = if (isRequired) Sub2 else Gray300,
                style = OffroadTheme.typography.textRegular,
            )
        }
        Text(
            text = text,
            color = Main2,
            style = OffroadTheme.typography.hint,
            modifier = Modifier
                .padding(start = 8.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(R.drawable.ic_agree_click_next),
            contentDescription = "next",
            alignment = Alignment.CenterEnd,
        )
    }
}
