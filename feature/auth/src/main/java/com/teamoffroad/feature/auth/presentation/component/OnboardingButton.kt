package com.teamoffroad.feature.auth.presentation.component

import androidx.compose.foundation.background
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
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.Black
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White

@Composable
fun OnboardingButton(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(6.dp),
    text: String,
    isActive: NicknameValidateResult,
    onButtonClick: () -> Unit,
) {
    val backGroundColor =
        if (isActive == NicknameValidateResult.NicknameValidateSuccess) 1.0f else 0.15f
    val keyboardController = LocalSoftwareKeyboardController.current


    Box(
        modifier = modifier
            .clickableWithoutRipple(enabled = isActive == NicknameValidateResult.NicknameValidateSuccess) {
                onButtonClick()
                keyboardController?.hide()
            }
            .fillMaxWidth()
            .background(color = Black.copy(alpha = backGroundColor), shape = shape)
            .padding(vertical = 8.dp, horizontal = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                fontSize = 13.sp,
                text = text,
                color = White,
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