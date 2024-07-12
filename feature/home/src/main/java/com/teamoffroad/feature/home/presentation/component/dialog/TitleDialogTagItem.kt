package com.teamoffroad.feature.home.presentation.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.feature.home.presentation.model.DummyDataModel

@Composable
fun DialogTagItem(
    text: String,
    textColor: Color,
    style: TextStyle,
    backgroundColor: Color,
    borderColor: Color = backgroundColor,
    dummyDataModel: DummyDataModel,
    onItemClick: (DummyDataModel) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Text(
        text = text,
        textAlign = TextAlign.Center,
        color = textColor,
        style = style,
        modifier = Modifier
            .clickableWithoutRipple(
                interactionSource = interactionSource,
                onClick = { onItemClick(dummyDataModel) }
            )
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(5.dp),
            )
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(5.dp),
                color = borderColor,
            )
            .padding(vertical = 12.dp)
    )
}
