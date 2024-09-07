package com.teamoffroad.feature.mypage.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.Black15
import com.teamoffroad.core.designsystem.theme.Error
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.Gray300
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.mypage.domain.model.UseCoupon
import com.teamoffroad.feature.mypage.presentation.model.CheckCouponState
import com.teamoffroad.offroad.feature.mypage.R

@Composable
fun UseAvailableCouponDialog(
    couponId: Int,
    couponCodeSuccess: CheckCouponState,
    couponCode: String,
    placeId: Int,
    errorMessage: String,
    showDialog: MutableState<Boolean>,
    onClickCancel: () -> Unit,
    updateCode: (String) -> Unit,
    updateCouponCodeSuccess: (CheckCouponState) -> Unit,
    saveCoupon: (UseCoupon) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(14.dp),
    backgroundColor: Color = Main3
) {

    Dialog(
        onDismissRequest = { onClickCancel() },
        properties = DialogProperties(
            dismissOnClickOutside = true,
            dismissOnBackPress = true,
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .imePadding()
                .padding(4.dp)
        ) {
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .height(218.dp),
                shape = shape,
            ) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(backgroundColor),
                    contentAlignment = Alignment.TopEnd
                ) {
                    CloseDialog(
                        onClickCancel = { showDialog.value = false }
                    )

                    UseAvailableCouponDialogText(
                        showDialog = showDialog,
                        couponCode = couponCode,
                        couponId = couponId,
                        placeId = placeId,
                        couponCodeSuccess = couponCodeSuccess,
                        updateCode = updateCode,
                        updateCouponCodeSuccess = updateCouponCodeSuccess,
                        saveCoupon = saveCoupon
                    )
                }
            }
        }
    }
}

@Composable
private fun UseAvailableCouponDialogText(
    showDialog: MutableState<Boolean>,
    couponCode: String,
    couponId: Int,
    placeId: Int,
    couponCodeSuccess: CheckCouponState,
    updateCode: (String) -> Unit,
    updateCouponCodeSuccess: (CheckCouponState) -> Unit,
    saveCoupon: (UseCoupon) -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color = Main2,
) {
    when (couponCodeSuccess) {
        CheckCouponState.NONE -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(start = 40.dp, top = 26.dp, end = 40.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.my_page_gained_coupon_use_coupon),
                    color = textColor,
                    style = OffroadTheme.typography.title,
                    modifier = modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Box(
                    modifier = modifier.weight(1f)
                ) {
                    Column {
                        Text(
                            text = stringResource(id = R.string.my_page_gained_coupon_use_coupon_description),
                            color = textColor,
                            style = OffroadTheme.typography.textRegular,
                            modifier = modifier
                                .padding(top = 14.dp)
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            textAlign = TextAlign.Center
                        )
                        CodeTextField(
                            updateCode = updateCode,
                            couponId = couponId,
                            placeId = placeId
                        )

                    }
                }
                ConfirmButton(
                    updateCode = updateCode,
                    updateCouponCodeSuccess = updateCouponCodeSuccess,
                    couponCode = couponCode,
                    couponId = couponId,
                    placeId = placeId,
                    couponCodeSuccess = couponCodeSuccess,
                    showDialog = showDialog,
                    saveCoupon = saveCoupon
                )
            }
        }

        CheckCouponState.SUCCESS -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(start = 40.dp, top = 26.dp, end = 40.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.my_page_gained_coupon_success_use),
                    color = textColor,
                    style = OffroadTheme.typography.title,
                    modifier = modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Box(
                    modifier = modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.my_page_gained_coupon_success_use_description),
                        color = textColor,
                        style = OffroadTheme.typography.textRegular,
                        modifier = modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                }
                ConfirmButton(
                    updateCode = updateCode,
                    updateCouponCodeSuccess = updateCouponCodeSuccess,
                    couponCode = couponCode,
                    couponId = couponId,
                    placeId = placeId,
                    couponCodeSuccess = couponCodeSuccess,
                    showDialog = showDialog,
                    saveCoupon = saveCoupon
                )
            }
        }

        CheckCouponState.FAIL -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(start = 40.dp, top = 26.dp, end = 40.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.my_page_gained_coupon_fail_use),
                    color = textColor,
                    style = OffroadTheme.typography.title,
                    modifier = modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Box(
                    modifier = modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = modifier
                            .padding(start = 24.dp)
                            .fillMaxWidth()
                            .align(Alignment.Center)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_my_page_error),
                            contentDescription = null,
                            modifier = modifier
                                .padding(end = 6.dp)
                                .size(22.dp)
                                .align(Alignment.CenterVertically)
                        )
                        Text(
                            text = stringResource(id = R.string.my_page_gained_coupon_fail_use_description),
                            color = Error,
                            style = OffroadTheme.typography.subtitle2Semibold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                ConfirmButton(
                    updateCode = updateCode,
                    updateCouponCodeSuccess = updateCouponCodeSuccess,
                    couponCode = couponCode,
                    couponId = couponId,
                    placeId = placeId,
                    couponCodeSuccess = couponCodeSuccess,
                    showDialog = showDialog,
                    saveCoupon = saveCoupon
                )
            }
        }
    }
}

@Composable
private fun CodeTextField(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(6.dp),
    placeholder: String = stringResource(id = R.string.my_page_gained_coupon_use_coupon_hint),
    couponId: Int,
    placeId: Int,
    maxLines: Int = 1,
    minLines: Int = 1,
    updateCode: (String) -> Unit,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val borderLineColor = remember { mutableStateOf(Gray100) }
    val couponCode = remember { mutableStateOf("") }

    BasicTextField(
        value = couponCode.value,
        onValueChange = { newCode ->
            couponCode.value = newCode
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
                    if (couponCode.value.isEmpty()) {
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
private fun ConfirmButton(
    updateCode: (String) -> Unit,
    couponCodeSuccess: CheckCouponState,
    updateCouponCodeSuccess: (CheckCouponState) -> Unit,
    saveCoupon: (UseCoupon) -> Unit,
    showDialog: MutableState<Boolean>,
    couponCode: String,
    couponId: Int,
    modifier: Modifier = Modifier,
    placeId: Int,
    textColor: Color = Main1,
    textStyle: TextStyle = OffroadTheme.typography.btnSmall,
    shape: Shape = RoundedCornerShape(6.dp)
) {
    val interactionSource = remember { MutableInteractionSource() }

    Text(
        text = stringResource(id = R.string.my_page_gained_coupon_confirm),
        color = textColor,
        style = textStyle,
        textAlign = TextAlign.Center,
        modifier = modifier
            .padding(bottom = 26.dp)
            .clip(shape = shape)
            .fillMaxWidth()
            .background(if (couponCode.isEmpty()) Black15 else Main2)
            .padding(vertical = 12.dp)
            .clickableWithoutRipple(interactionSource = interactionSource) {
                when (couponCodeSuccess) {
                    CheckCouponState.NONE -> {
                        if (couponCode.isNotBlank()) {
                            saveCoupon(UseCoupon(couponCode, couponId, placeId))
                            //saveCoupon(UseCoupon(couponCode, 2, 855)) // test용
                        }
                    }

                    CheckCouponState.SUCCESS -> {
                        updateCode("")
                        showDialog.value = false
                    }

                    CheckCouponState.FAIL -> {
                        updateCode("")
                        updateCouponCodeSuccess(CheckCouponState.NONE)
                        showDialog.value = false
                    }
                }
            }
    )
}

@Composable
private fun CloseDialog(
    onClickCancel: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(onClick = { onClickCancel() }) {
        Image(
            painter = painterResource(id = R.drawable.ic_mypage_close),
            contentDescription = null,
            modifier = modifier.padding(top = 6.dp, end = 4.dp)
        )
    }
}