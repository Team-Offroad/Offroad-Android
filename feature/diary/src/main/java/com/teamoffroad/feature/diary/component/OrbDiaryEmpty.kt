package com.teamoffroad.feature.diary.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.Black55
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.offroad.feature.diary.R

@Composable
fun OrbDiaryEmpty(
    navigateToCharacterChat: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_diary_empty),
            contentDescription = "empty",
            modifier = Modifier.padding(bottom = 25.dp),
        )
        Text(
            text = stringResource(id = R.string.diary_empty_content),
            textAlign = TextAlign.Center,
            color = Black55.copy(alpha = 0.55f),
            style = OffroadTheme.typography.boxMedi,
            modifier = Modifier.padding(bottom = 24.dp),
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(
                    color = Main2,
                    shape = RoundedCornerShape(46.dp)
                )
                .padding(horizontal = 68.dp, vertical = 14.dp)
                .clickableWithoutRipple { navigateToCharacterChat("???") },
        ) {
            Text(
                text = stringResource(id = R.string.diary_empty_go_chatting),
                color = Main1,
                style = OffroadTheme.typography.btnSmall,
            )
        }
    }
}