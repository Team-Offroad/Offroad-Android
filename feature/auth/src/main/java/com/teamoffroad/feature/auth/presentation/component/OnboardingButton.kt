package com.teamoffroad.feature.auth.presentation.component

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teamoffroad.core.designsystem.theme.Black
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.Gray300
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White

@Composable
fun OnboardingButton(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(6.dp),
    text: String,
    isActive: ValidateResult,
    onButtonClick: () -> Unit,
) {
    val (borderLineColor, contentColor, backGroundColor) = if (isActive == ValidateResult.ValidateSuccess) {
        Triple(Black, White, Black)
    } else {
        Triple(Gray100, Gray300, White)
    }
    val keyboardController = LocalSoftwareKeyboardController.current


    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(backGroundColor, shape = shape)
            .border(
                width = 1.dp,
                color = if (isActive == ValidateResult.ValidateSuccess) borderLineColor else borderLineColor,
                shape = shape
            )
            .padding(vertical = 8.dp, horizontal = 6.dp)
            .clickable(enabled = isActive == ValidateResult.ValidateSuccess,
                onClick = {
                    onButtonClick()
                    keyboardController?.hide()
                }),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                fontSize = 13.sp,
                text = text,
                color = if (isActive == ValidateResult.ValidateSuccess) contentColor else contentColor,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun OnboardingButtonPreview() {
    OffroadTheme {
        var normalValue by remember {
            mutableStateOf("")
        }

    }
}