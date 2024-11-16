package com.teamoffroad.characterchat.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White25
import java.time.LocalDate

@Composable
fun DateDivider(
    date: LocalDate,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "8월 27일 화요일",
            color = Main3,
            style = OffroadTheme.typography.boxMedi,
        )
        HorizontalDivider(
            color = White25,
            modifier = Modifier.padding(top = 6.dp),
        )
    }
}
