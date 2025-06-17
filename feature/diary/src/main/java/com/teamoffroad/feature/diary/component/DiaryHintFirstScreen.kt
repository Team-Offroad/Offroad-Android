package com.teamoffroad.feature.diary.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.offroad.feature.diary.R

@Composable
fun DiaryHintFirstScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_diary_hint_character),
            contentDescription = "character",
            modifier = Modifier
                .height(240.dp)
        )
        Text(
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.diary_dialog_hint_first_title),
            color = White,
            style = OffroadTheme.typography.textRegular,
            modifier = Modifier.padding(top = 40.dp, bottom = 24.dp),
        )
        Text(
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.diary_dialog_hint_first_content),
            color = White,
            style = OffroadTheme.typography.textRegular,
        )
    }
}