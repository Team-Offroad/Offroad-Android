package com.teamoffroad.characterchat.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Transparent

@Composable
fun CharacterChatHeader(
    modifier: Modifier = Modifier,
    characterName: String,
    navigateToBack: () -> Unit,
) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .padding(top = 12.dp),
        contentAlignment = Alignment.Center,
    ) {
        NavigateBackAppBar(
            mainColor = Main3,
            backgroundColor = Transparent,
        ) {
            navigateToBack()
        }
        Text(
            text = characterName,
            textAlign = TextAlign.Center,
            color = Main3,
            style = OffroadTheme.typography.textBold,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
