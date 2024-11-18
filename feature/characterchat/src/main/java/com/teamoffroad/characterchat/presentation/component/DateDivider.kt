package com.teamoffroad.characterchat.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White25
import com.teamoffroad.offroad.feature.characterchat.R
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun DateDivider(
    date: LocalDate,
) {
    val dayOfWeek = date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.character_chat_date, date.monthValue, date.dayOfMonth, dayOfWeek),
            color = Main3,
            style = OffroadTheme.typography.boxMedi,
        )
        HorizontalDivider(
            color = White25,
            modifier = Modifier.padding(top = 6.dp),
        )
    }
}
