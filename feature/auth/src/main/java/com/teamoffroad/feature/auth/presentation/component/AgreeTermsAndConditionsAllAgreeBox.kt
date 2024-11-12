package com.teamoffroad.feature.auth.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.NametagInactive
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.offroad.feature.auth.R

@Composable
fun AgreeTermsAndConditionsTopBarAllAgreeBox(
    isChecked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(horizontal = 34.dp)
            .clip(shape = RoundedCornerShape(5.dp))
            .background(NametagInactive)
            .clickableWithoutRipple { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isChecked) {
                Image(
                    painter = painterResource(R.drawable.ic_agree_check_fill),
                    contentDescription = "check",
                    modifier = Modifier.padding(end = 10.dp)
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.ic_agree_check_empty),
                    contentDescription = "check",
                    modifier = Modifier.padding(end = 10.dp)
                )
            }
            Text(
                text = stringResource(R.string.auth_agree_and_terms_conditions_all_agree),
                color = Main2,
                style = OffroadTheme.typography.textBold,
                textAlign = TextAlign.Center
            )
        }
    }
}