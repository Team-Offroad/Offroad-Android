package com.teamoffroad.feature.auth.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.ui.res.painterResource
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
    modifier: Modifier = Modifier,
    dialogShown: () -> Unit,
    onClick: () -> Unit,
) {
    Box(Modifier.clickableWithoutRipple { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 44.dp)
                .padding(bottom = 18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (isChecked) {
                    Image(
                        painter = painterResource(R.drawable.ic_agree_check_fill),
                        contentDescription = "check",
                        modifier = Modifier.padding(end = 12.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(R.drawable.ic_agree_check_empty),
                        contentDescription = "check",
                        modifier = Modifier.padding(end = 12.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = if (isChecked) Sub2 else Gray300,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "필수",
                        color = if (isChecked) Sub2 else Gray300,
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
            }
            Image(
                painter = painterResource(R.drawable.ic_agree_click_next),
                contentDescription = "next",
                modifier = Modifier.clickableWithoutRipple { dialogShown() }
            )
        }
    }
}