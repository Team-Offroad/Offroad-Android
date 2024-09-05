package com.teamoffroad.feature.mypage.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.teamoffroad.core.designsystem.component.GestureNavigation
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.Contents2
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.Gray400
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub2
import com.teamoffroad.core.designsystem.theme.Sub4
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.mypage.presentation.component.UseAvailableCouponDialog
import com.teamoffroad.feature.mypage.presentation.model.CheckCouponState
import com.teamoffroad.offroad.feature.mypage.R

@Composable
fun AvailableCouponDetailScreen(
    couponId: Int,
    name: String,
    couponImageUrl: String,
    description: String,
    placeId: Int,
    navigateToGainedCoupon: () -> Unit,
    availableCouponDetailViewModel: AvailableCouponDetailViewModel = hiltViewModel(),
    backgroundColor: Color = ListBg
) {
    val couponCode = availableCouponDetailViewModel.couponCode.collectAsStateWithLifecycle().value
    val couponCodeSuccess =
        availableCouponDetailViewModel.couponCodeSuccess.collectAsStateWithLifecycle().value

    Box(
        modifier = Modifier
            .then(GestureNavigation())
            .background(Sub4)
    ) {
        Column(
            modifier = Modifier
                .background(backgroundColor)
                .fillMaxSize(),
        ) {
            OffroadActionBar()
            NavigateBackAppBar(
                modifier = Modifier.padding(top = 20.dp),
                text = stringResource(id = R.string.my_page_my_page),
                backgroundColor = backgroundColor
            ) { navigateToGainedCoupon() }
            AvailableCouponCard(name, couponImageUrl, description, placeId)
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                WayOfUse()
            }
            UseAvailableCouponButton(
                couponId = couponId,
                couponCode = couponCode,
                updateCouponCodeSuccess = availableCouponDetailViewModel::updateCouponCodeSuccess,
                couponCodeSuccess = couponCodeSuccess,
                updateCode = availableCouponDetailViewModel::updateCode
            )
        }
    }
}

@Composable
fun AvailableCouponCard(
    name: String,
    couponImageUrl: String,
    description: String,
    placeId: Int,
    shape: Shape = RoundedCornerShape(20.dp),
    borderWidth: Dp = 1.dp,
    textColor: Color = Main2,
    backgroundColor: Color = Main1
) {
    Box(
        modifier = Modifier
            .padding(start = 30.dp, top = 14.dp, end = 30.dp)
            .border(
                width = borderWidth,
                shape = shape,
                color = Contents2
            )
            .clip(shape = shape)
            .background(backgroundColor)
            .padding(start = 20.dp, top = 20.dp, end = 20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = couponImageUrl,
                contentDescription = "couponImageUrl",
                modifier = Modifier
                    .aspectRatio(260f / 260f)
                    .clip(shape = shape)
                    .fillMaxWidth()
                    .border(
                        width = borderWidth,
                        shape = shape,
                        color = Gray100
                    ),
            )
            Text(
                text = name,
                modifier = Modifier
                    .padding(top = 14.dp),
                color = textColor,
                style = OffroadTheme.typography.textBold
            )
            Image(
                painterResource(id = R.drawable.img_line),
                contentDescription = "line",
                alignment = Alignment.Center,
                modifier = Modifier.padding(start = 8.dp, top = 12.dp, end = 8.dp)
            )
            Text(
                text = description,
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 18.dp),
                textAlign = TextAlign.Center,
                color = textColor,
                style = OffroadTheme.typography.textRegular,
            )
        }
    }
}

@Composable
private fun WayOfUse() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, top = 12.dp, end = 30.dp)
            .padding(start = 6.dp, top = 6.dp, end = 6.dp)
    ) {
        Text(
            text = stringResource(id = R.string.my_page_gained_coupon_way_of_use),
            color = Sub2,
            style = OffroadTheme.typography.textBold,
            modifier = Modifier
        )
        Row(
            modifier = Modifier.padding(top = 12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_mypage_way_of_use),
                contentDescription = null
            )
            Text(
                text = stringResource(id = R.string.my_page_gained_coupon_way_of_use_description),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                color = Gray400,
                style = OffroadTheme.typography.textRegular
            )
        }
    }
}

@Composable
private fun UseAvailableCouponButton(
    couponId: Int,
    couponCode: String,
    updateCouponCodeSuccess: (CheckCouponState) -> Unit,
    couponCodeSuccess: CheckCouponState,
    updateCode: (String) -> Unit,
) {
    val isUseAvailableCouponDialogShown = remember { mutableStateOf(false) }

    Text(
        text = stringResource(id = R.string.my_page_gained_coupon_use_available_coupon),
        color = White,
        style = OffroadTheme.typography.textRegular,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, top = 26.dp, end = 24.dp, bottom = 24.dp)
            .clip(shape = RoundedCornerShape(6.dp))
            .background(Main2)
            .padding(vertical = 14.dp)
            .clickableWithoutRipple(interactionSource = remember {
                MutableInteractionSource()
            }) {
                isUseAvailableCouponDialogShown.value = true
            }
    )

    if (isUseAvailableCouponDialogShown.value) {
        UseAvailableCouponDialog(
            couponId = couponId,
            updateCouponCodeSuccess = updateCouponCodeSuccess,
            couponCodeSuccess = couponCodeSuccess,
            couponCode = couponCode,
            showDialog = isUseAvailableCouponDialogShown,
            onClickCancel = {
                isUseAvailableCouponDialogShown.value = false
            },
            updateCode = updateCode
        )
    }
}