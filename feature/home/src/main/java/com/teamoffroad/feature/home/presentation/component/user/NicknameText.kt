package com.teamoffroad.feature.home.presentation.component.user

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.PretendardBold
import com.teamoffroad.offroad.feature.home.R

@Composable
fun NicknameText(nickname: String) {
    Text(
        modifier = Modifier.padding(start = 24.dp, top = 32.dp),
        style = OffroadTheme.typography.bothSubtitle3,

        text = buildAnnotatedString {
            append(stringResource(id = R.string.home_explorer))
            append(" ")
            withStyle(
                SpanStyle(
                    fontFamily = PretendardBold,
                    fontWeight = FontWeight.Bold,
                )
            ) {
                append(nickname)
            }
            append(stringResource(id = R.string.home_explorer_suffix))


        }
    )
}
