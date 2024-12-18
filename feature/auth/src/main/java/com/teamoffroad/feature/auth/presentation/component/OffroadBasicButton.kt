package com.teamoffroad.feature.auth.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Black
import com.teamoffroad.core.designsystem.theme.White

@Composable
fun OffroadBasicBtn(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(6.dp),
    text: String,
    isActive: Boolean,
    updateState: () -> Unit = {},
    onClick: () -> Unit,
) {
    val backGroundOpacity = if (isActive) 1.0f else 0.15f
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Black.copy(alpha = backGroundOpacity), shape = shape)
            .padding(vertical = 8.dp, horizontal = 6.dp)
            .clickable(
                enabled = isActive,
                onClick = {
                    onClick()
                    updateState()
                }
            ),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                fontSize = with(LocalDensity.current) { 13.dp.toSp() },
                text = text,
                color = White,
            )
        }
    }
}