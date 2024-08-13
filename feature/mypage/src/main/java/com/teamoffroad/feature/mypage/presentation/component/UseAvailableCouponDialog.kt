package com.teamoffroad.feature.mypage.presentation.component

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.Gray300
import com.teamoffroad.core.designsystem.theme.Kakao
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.offroad.feature.mypage.R

@Composable
fun UseAvailableCouponDialog(
    couponCode: String,
    showDialog: MutableState<Boolean>,
    onClickCancel: () -> Unit,
    updateCode: (String) -> Unit,
) {
    Dialog(
        onDismissRequest = { onClickCancel() },
        properties = DialogProperties(dismissOnClickOutside = true, dismissOnBackPress = true)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Main3),
                contentAlignment = Alignment.TopEnd
            ) {
                CloseDialog(
                    onClickCancel = { showDialog.value = false }
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp, top = 26.dp, end = 40.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.gained_coupon_use_coupon),
                        color = Main2,
                        style = OffroadTheme.typography.title,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(id = R.string.gained_coupon_use_coupon_description),
                        color = Main2,
                        style = OffroadTheme.typography.textRegular,
                        modifier = Modifier
                            .padding(top = 14.dp)
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        textAlign = TextAlign.Center
                    )
                    CodeTextField(updateCode = updateCode, couponCode = couponCode)
                    ConfirmButton()
                }
            }
        }
    }
}

@Composable
private fun CodeTextField(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(6.dp),
    placeholder: String = stringResource(id = R.string.gained_coupon_use_coupon_hint),
    couponCode: String = "",
    maxLines: Int = 1,
    minLines: Int = 1,
    updateCode: (String) -> Unit,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val borderLineColor = remember { mutableStateOf(Gray100) }

    BasicTextField(
        value = couponCode,
        onValueChange = { newCode ->
            updateCode(newCode)
        },
        singleLine = maxLines == 1,
        maxLines = if (minLines > maxLines) minLines else maxLines,
        minLines = minLines,
        interactionSource = interactionSource,
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
                        shape = shape
                    )
                    .padding(start = 10.dp, top = 12.dp, bottom = 12.dp)
            ) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .clip(shape = shape),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (couponCode.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = Gray300,
                            style = OffroadTheme.typography.hint,
                            maxLines = 1,
                        )
                    }
                    innerText()
                }
            }
        }
    )
}

@Composable
private fun ConfirmButton() {
    Text(
        text = stringResource(id = R.string.gained_coupon_confirm),
        color = Main1,
        style = OffroadTheme.typography.btnSmall,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(top = 18.dp, bottom = 26.dp)
            .clip(shape = RoundedCornerShape(6.dp))
            .fillMaxWidth()
            .background(Main2)
            .padding(vertical = 12.dp)
            .clickableWithoutRipple(interactionSource = MutableInteractionSource()) {
                // 확인 버튼 클릭
            }
    )
}

@Composable
private fun CloseDialog(
    onClickCancel: () -> Unit
) {
    IconButton(onClick = { onClickCancel() }) {
        Image(
            painter = painterResource(id = R.drawable.ic_mypage_close),
            contentDescription = null,
            modifier = Modifier.padding(top = 6.dp, end = 4.dp)
        )
    }
}