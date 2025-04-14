package com.teamoffroad.feature.recommendplace.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.Gray300
import com.teamoffroad.core.designsystem.theme.Gray400
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.offroad.feature.recommendplace.R

@Composable
fun RecommendPlaceOrderEtc(
    etcText: String,
    onEtcTextChanged: (String) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val maxLines = 7

    Column(
        modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 34.dp)
    ) {
        Text(
            text = stringResource(id = R.string.recommend_place_order_question_etc),
            style = OffroadTheme.typography.textBold,
            color = Main2
        )

        Box {
            BasicTextField(
                value = etcText,
                onValueChange = {
                    val lines = it.lines()
                    if (lines.size <= maxLines) {
                        onEtcTextChanged(it)
                    } else {
                        onEtcTextChanged(lines.take(maxLines).joinToString("\n"))
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .heightIn(min = 202.dp)
                    .background(Main3, RoundedCornerShape(6.dp))
                    .border(1.dp, if (isFocused) Main2 else Gray100, RoundedCornerShape(6.dp))
                    .padding(horizontal = 12.dp, vertical = 14.dp),
                cursorBrush = SolidColor(Main2),
                interactionSource = interactionSource,
                singleLine = false,
                decorationBox = { innerTextField ->
                    Box {
                        if (etcText.isEmpty()) {
                            Text(
                                text = stringResource(id = R.string.recommend_place_order_question_etc_placeholder),
                                style = OffroadTheme.typography.textAuto,
                                color = Gray300
                            )
                        }
                        innerTextField()
                    }
                }
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = OffroadTheme.typography.hint.toSpanStyle().copy(color = Gray400)
                    ) {
                        append("${etcText.length}")
                    }
                    append(" / 200")
                },
                style = OffroadTheme.typography.hint,
                color = Gray300,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 14.dp, end = 10.dp)
            )

        }
    }
}