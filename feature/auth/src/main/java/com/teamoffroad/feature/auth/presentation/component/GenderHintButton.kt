package com.teamoffroad.feature.auth.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Black
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.Gray300
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.NametagInactive
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub

@Composable
fun GenderHintButton(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(6.dp),
    value: String = "",
    isActive: Boolean,
) {
    val (borderLineColor, contentColor, backGroundColor) = if (isActive) {
        Triple(Sub, Black, NametagInactive)
    } else {
        Triple(Gray100, Gray300, Main3)
    }

    Box {
        Text(
            modifier = modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .background(backGroundColor, shape = shape)
                .border(
                    width = 1.dp,
                    color = borderLineColor,
                    shape = shape
                )
                .padding(vertical = 18.dp),
            text = value,
            color = contentColor,
            style = OffroadTheme.typography.textAuto,
            textAlign = TextAlign.Center,
        )
    }
}