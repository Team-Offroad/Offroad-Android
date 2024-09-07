package com.teamoffroad.core.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.teamoffroad.core.designsystem.theme.ListBg

@Composable
fun Modifier.clickableSettingRipple(
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    onClick: () -> Unit,
): Modifier {
    val rememberRipple = rememberRipple(
        color = ListBg,
    )

    return this
        .clickable(
            interactionSource = interactionSource,
            indication = rememberRipple,
            onClick = onClick
        )
}
