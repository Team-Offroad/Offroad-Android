package com.teamoffroad.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Main1

@Composable
fun OffroadActionBar(color: Color = Main1) {
    Spacer(
        modifier = Modifier
            .height(34.dp)
            .background(color)
    )
}

fun Modifier.actionBarPadding(): Modifier {
    return this.padding(top = 34.dp)
}
