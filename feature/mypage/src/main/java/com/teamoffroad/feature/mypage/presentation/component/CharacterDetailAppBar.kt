package com.teamoffroad.feature.mypage.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.core.designsystem.theme.Sub55
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.offroad.feature.mypage.R

@Composable
fun CharacterDetailAppBar(
    modifier: Modifier = Modifier,
    backgroundColor: Long,
    navigateToBack: () -> Unit,
    onChatLogClick: () -> Unit,
) {
    Box(modifier = modifier.padding(top = 20.dp)) {
        NavigateBackAppBar(
            text = stringResource(R.string.my_page_gained_character),
            backgroundColor = Color(backgroundColor),
            navigateToBack = navigateToBack,
        )
        Text(
            text = stringResource(R.string.my_page_character_chat_log),
            style = OffroadTheme.typography.textContents,
            color = White,
            modifier = Modifier
                .padding(end = 24.dp)
                .align(Alignment.CenterEnd)
                .background(
                    color = Sub55,
                    shape = RoundedCornerShape(32.dp)
                )
                .border(
                    width = 1.dp,
                    color = Sub,
                    shape = RoundedCornerShape(32.dp)
                )
                .padding(vertical = 10.dp, horizontal = 16.dp)
                .clickableWithoutRipple { onChatLogClick() },
        )
    }
}
