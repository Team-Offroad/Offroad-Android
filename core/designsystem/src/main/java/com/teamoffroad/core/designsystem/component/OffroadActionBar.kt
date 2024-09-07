package com.teamoffroad.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Main1

fun Modifier.actionBarPadding(color: Color = Main1): Modifier {
    return this
        .background(color)
        .padding(top = 34.dp)
}
