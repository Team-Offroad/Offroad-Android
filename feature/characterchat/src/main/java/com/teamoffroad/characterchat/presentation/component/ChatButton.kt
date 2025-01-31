package com.teamoffroad.characterchat.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.StaticAnimationWrapper
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.core.designsystem.theme.White25
import com.teamoffroad.offroad.feature.characterchat.R

@Composable
fun ChatButton(
    modifier: Modifier = Modifier,
    isVisible: Boolean = false,
    onClick: () -> Unit = {},
) {
    StaticAnimationWrapper(visible = isVisible) {
        Text(
            text = stringResource(R.string.character_chat_chat),
            color = White,
            style = OffroadTheme.typography.textRegular,
            modifier = modifier
                .clickableWithoutRipple { onClick() }
                .background(color = White25, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 18.dp, vertical = 8.dp)
                .wrapContentSize(),
        )
    }
}

@Preview
@Composable
fun ChatButtonPreview() {
    ChatButton()
}
