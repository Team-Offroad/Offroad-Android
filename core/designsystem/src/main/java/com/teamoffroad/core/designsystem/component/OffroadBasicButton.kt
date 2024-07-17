package com.teamoffroad.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teamoffroad.core.designsystem.theme.Black
import com.teamoffroad.core.designsystem.theme.Gray400
import com.teamoffroad.core.designsystem.theme.White

@Composable
fun OffroadBasicBtn(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(6.dp),
    text: String,
    isActive: Boolean,
    onClick: () -> Unit,
) {
    val (borderLineOpacity, contentColor, backGroundOpacity) = if (isActive) {
        Triple(1.0f, White, 1.0f)
    } else {
        Triple(0.25f, Gray400, 0.15f)
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Black.copy(alpha = backGroundOpacity), shape = shape)
            .border(
                width = 1.dp,
                color = Black.copy(alpha = borderLineOpacity),
                shape = shape
            )
            .padding(vertical = 8.dp, horizontal = 6.dp)
            .clickable(
                enabled = isActive,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                fontSize = 13.sp,
                text = text,
                color = contentColor,
            )
        }
    }
}