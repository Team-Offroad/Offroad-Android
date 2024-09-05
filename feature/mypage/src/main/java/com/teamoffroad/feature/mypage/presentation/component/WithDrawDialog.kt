package com.teamoffroad.feature.mypage.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub2
import com.teamoffroad.core.designsystem.theme.White
import kotlin.reflect.KFunction1

@Composable
fun WithDrawDialog(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(14.dp),
    onInputTextChange: KFunction1<String, Unit>,
    isWithDrawText: String,
    isWithDrawResult: Boolean,
    isWithDrawResultChanged: () -> Unit,
    onClickCancel: () -> Unit,
) {

    Dialog(
        onDismissRequest = { onClickCancel() },
        properties = DialogProperties(dismissOnClickOutside = true, dismissOnBackPress = true)
    ) {
        Box(
            modifier = modifier
                .background(shape = shape, color = Main3),
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 22.dp, horizontal = 40.dp)
                    .align(Alignment.Center)
            ) {
                Text(
                    text = "회원 탈퇴",
                    color = Main2,
                    style = OffroadTheme.typography.title,
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .padding(bottom = 16.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "정말 탈퇴하시겠어요? \n탈퇴하다면 아래 문구를 입력창에\n그대로 입력해주세요.",
                    color = Main2,
                    style = OffroadTheme.typography.textRegular,
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .padding(bottom = 16.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "오프로드 회원을 탈퇴하겠습니다.",
                    color = Sub2,
                    style = OffroadTheme.typography.textBold,
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .padding(bottom = 8.dp)
                        .align(Alignment.CenterHorizontally)
                )
                WithdrawTextField(
                    modifier = modifier
                        .align(Alignment.CenterHorizontally),
                    value = isWithDrawText,
                    onValueChanged = onInputTextChange,
                    isWithDrawResultChanged = isWithDrawResultChanged,
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    WithDrawButton(
                        text = "취소",
                        onClick = onClickCancel,
                        textColor = Main2,
                        backgroundColor = Main3,
                        modifier = Modifier.weight(1f)
                    )
                    WithDrawButton(
                        text = "탈퇴",
                        onClick = {},
                        textColor = White,
                        backgroundColor = Main2,
                        modifier = Modifier.weight(1f),
                        isWithDrawResult = isWithDrawResult,
                    )
                }
            }
        }
    }
}

@Composable
private fun WithDrawButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    textColor: Color,
    backgroundColor: Color,
    isWithDrawResult: Boolean = true,
) {
    Text(text = text,
        color = textColor,
        style = OffroadTheme.typography.btnSmall,
        textAlign = TextAlign.Center,
        modifier = modifier
            .background(
                color =
                if (!isWithDrawResult) {
                    backgroundColor.copy(alpha = 0.15f)
                } else
                    backgroundColor,
                shape = RoundedCornerShape(5.dp),
            )
            .border(width = 1.dp, shape = RoundedCornerShape(5.dp), color = if (!isWithDrawResult) {
                backgroundColor.copy(alpha = 0.15f)
            } else
                Main2,)
            .padding(vertical = 14.dp, horizontal = 38.dp)
            .clickableWithoutRipple { onClick() })
}