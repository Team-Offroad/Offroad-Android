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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
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
    value: String = "",
    nicknameValidateResult: NicknameValidateResult,
    onValueChange: (String) -> Unit = { _ -> },
    maxLines: Int = 1,
    minLines: Int = 1,
    maxLength: Int = 16,
    textStyle: TextStyle = OffroadTheme.typography.textAuto,
    textAlign: Alignment,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val isFocused by interactionSource.collectIsFocusedAsState()

    val borderLineColor = remember { mutableStateOf(Gray100) }
    val textColor = remember { mutableStateOf(Gray300) }

    if (isFocused) {
        borderLineColor.value = Sub
        textColor.value = Main2
    } else {
        borderLineColor.value = Gray100
        textColor.value = Gray300
    }

    val updatedTextStyle = textStyle.copy(color = textColor.value)

    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = { newValue ->
            val englishText = newValue.filter { it.isEnglish() }
            val koreanText = newValue.filter { it.isKorean() }

            val isEnglishValid = englishText.length <= 16
            val isKoreanValid = koreanText.length <= 8

            if (isEnglishValid || isKoreanValid) {
                if (newValue.replace(" ", "").length <= maxLength) onValueChange(newValue)

            }
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
                        color = borderLineColor.value,
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


fun Char.isEnglish(): Boolean {
    return this in 'A'..'Z' || this in 'a'..'z'
}

fun Char.isKorean(): Boolean {
    return this in '\uAC00'..'\uD7A3'
}