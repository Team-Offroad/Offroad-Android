package com.teamoffroad.feature.recommendplace.presentation.component

import android.graphics.Rect
import android.view.ViewTreeObserver
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
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
    setScrollHeight: (Dp) -> Unit,
    setKeyboardHeight: (Int) -> Unit,
    etcText: String,
    onEtcTextChanged: (String) -> Unit,
    setEtcTextFieldIsFocused: (Boolean) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val density = LocalDensity.current
    val contextView = LocalView.current
    val maxLines = 7

    // text field y 값
    var fieldBottomY by remember { mutableIntStateOf(0) }
    val distanceFromBottom =
        with(density) { LocalConfiguration.current.screenHeightDp.dp.toPx() } - fieldBottomY

    // keyboard 높이
    val textFieldHeight = remember { mutableIntStateOf(0) }
    val heightDp = with(density) { (textFieldHeight.intValue - distanceFromBottom).toInt().toDp() }
    val textFieldHeightDp = with(density) { textFieldHeight.intValue.toDp() }

    LaunchedEffect(textFieldHeightDp, heightDp) {
        if (textFieldHeightDp > 0.dp) {
            setScrollHeight(textFieldHeightDp - heightDp + 20.dp)
        }
    }

    LaunchedEffect(isFocused) {
        if (isFocused) setEtcTextFieldIsFocused(true)
        else setEtcTextFieldIsFocused(false)
    }

    DisposableEffect(contextView) {
        val rect = Rect()
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            contextView.getWindowVisibleDisplayFrame(rect)
            val screenHeight = contextView.rootView.height
            val keypadHeight = screenHeight - rect.bottom
            setKeyboardHeight(if (keypadHeight > screenHeight * 0.15) keypadHeight else 0)  // 키보드 올라옴 여부
        }
        contextView.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            contextView.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }

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
                    .onGloballyPositioned { layoutCoordinates ->
                        val position = layoutCoordinates.positionInRoot()
                        val height = layoutCoordinates.size.height
                        fieldBottomY = (position.y + height).toInt()
                        textFieldHeight.intValue = layoutCoordinates.size.height
                    }
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