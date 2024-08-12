package com.teamoffroad.feature.mypage.presentation.component

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.Black25
import com.teamoffroad.core.designsystem.theme.Contents2
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.mypage.presentation.model.FakeGainedCouponModel
import com.teamoffroad.offroad.feature.mypage.R

@Composable
fun AvailableCouponItems(
    coupons: List<FakeGainedCouponModel.FakeAvailableCouponsModel>,
    context: Context
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .background(ListBg)
            .padding(horizontal = 24.dp),
        contentPadding = PaddingValues(vertical = 18.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(coupons.size) { index ->
            AvailableCouponItem(coupons[index], context)
        }
    }
}

@Composable
fun AvailableCouponItem(
    coupon: FakeGainedCouponModel.FakeAvailableCouponsModel,
    context: Context
) {
    Box(
        modifier = Modifier
            .aspectRatio(148f / 176f)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(12.dp),
                color = Contents2
            )
            .clip(shape = RoundedCornerShape(12.dp))
            .padding(start = 8.dp, top = 8.dp, end = 8.dp)
            .clickableWithoutRipple(interactionSource = remember {
                MutableInteractionSource()
            }) {
                // open
            }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = coupon.couponImageUrl,
                contentDescription = "couponImageUrl",
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(12.dp))
                    .fillMaxWidth(),
            )
            Text(
                text = coupon.name,
                modifier = Modifier
                    .wrapContentHeight()
                    .weight(1f),
                color = Main2,
                style = OffroadTheme.typography.textContentsSmall,
            )
        }
    }
}

@Composable
fun UsedCouponItems(
    coupons: List<FakeGainedCouponModel.FakeUsedCouponsModel>,
    context: Context
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .background(ListBg)
            .padding(horizontal = 24.dp),
        contentPadding = PaddingValues(vertical = 18.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(coupons.size) { index ->
            UsedCouponItem(coupons[index], context)
        }
    }
}

@Composable
fun UsedCouponItem(
    coupon: FakeGainedCouponModel.FakeUsedCouponsModel,
    context: Context
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(148f / 176)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(12.dp),
                color = Contents2.copy(alpha = 0.25f)
            )
            .clip(shape = RoundedCornerShape(12.dp))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = coupon.couponImageUrl,
                contentDescription = "couponImageUrl",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp)
                    .clip(shape = RoundedCornerShape(12.dp)),
            )
            Text(
                text = coupon.name,
                modifier = Modifier
                    .wrapContentHeight()
                    .weight(1f),
                color = Main2,
                style = OffroadTheme.typography.textContentsSmall
            )
        }

        GainedCouponLockedCover()
    }
}

@Composable
private fun GainedCouponLockedCover() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Black25,
                shape = RoundedCornerShape(size = 10.dp)
            )
            .padding(start = 60.dp, end = 60.dp, top = 60.dp, bottom = 88.dp)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.ic_my_page_coupon_locked),
            contentDescription = "locked",
            alignment = Alignment.Center,
        )
    }
}