package com.teamoffroad.feature.auth.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.Gray300
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.core.designsystem.theme.White

@Composable
fun NicknameTextField(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(6.dp),
    placeholder: String = "",
    labelText: String = "",
    value: String = "",
    onValueChange: (String) -> Unit = { _ -> },
    isError: Boolean = false,
    maxLines: Int = 1,
    minLines: Int = 1,
    maxLength: Int = 10,
    textStyle: TextStyle = OffroadTheme.typography.textAuto,
    textAlign: Alignment,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val isFocused by interactionSource.collectIsFocusedAsState()

    val borderLineColor = when {
        isFocused -> Sub
        value.isEmpty() -> Gray100
        else -> Gray100
    }

    val textColor: Color = when {
        isFocused -> Main2
        value.isEmpty() -> Gray300
        else -> Gray300
    }
    val updatedTextStyle = textStyle.copy(color = textColor)

    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = { newValue ->
            if (newValue.replace(" ", "").length <= maxLength) onValueChange(newValue)
        },
        singleLine = maxLines == 1,
        textStyle = updatedTextStyle,
        maxLines = if (minLines > maxLines) minLines else maxLines,
        minLines = minLines,
        interactionSource = interactionSource,
        cursorBrush = SolidColor(Main1),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation,
        decorationBox = { innerText ->
            Column(
                modifier = modifier
                    .background(
                        color = White,
                        shape = shape
                    )
                    .border(
                        width = 1.dp,
                        color = borderLineColor,
                        shape = shape,
                    )
                    .padding(vertical = 12.dp, horizontal = 12.dp),
            ) {
                Box(
                    modifier = modifier
                        .clip(shape = shape),
                    contentAlignment = textAlign,
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = Gray300,
                            style = textStyle,
                            maxLines = 1,
                            overflow = TextOverflow.Clip,
                        )
                    }
                    innerText()
                }
            }
        },
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun NicknameTextFieldPreview() {
    OffroadTheme {
        var normalValue by remember {
            mutableStateOf("")
        }
    }
}