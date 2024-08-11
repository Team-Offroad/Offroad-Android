package com.teamoffroad.feature.mypage.presentation.component.coupon

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.teamoffroad.core.designsystem.theme.Contents2
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.mypage.presentation.component.coupon.model.FakeGainedCouponModel

@Composable
fun GainedAvailableCouponItems(
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
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(coupons.size) { index ->
            GainedAvailableCouponItem(coupons[index], context)
        }
    }
}

@Composable
fun GainedAvailableCouponItem(
    coupon: FakeGainedCouponModel.FakeAvailableCouponsModel,
    context: Context
) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(12.dp),
                color = Contents2
            )
            .clip(shape = RoundedCornerShape(12.dp))
            .padding(start = 8.dp, top = 8.dp, end = 8.dp)
    ) {
        Column {
            AsyncImage(
                model = coupon.couponImageUrl,
                contentDescription = "couponImageUrl",
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(12.dp))
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = coupon.name,
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 12.dp)
                    .align(Alignment.CenterHorizontally),
                color = Main2,
                style = OffroadTheme.typography.textContentsSmall
            )
        }
    }

}

@Composable
fun GainedUsedCouponItems(
    coupons: List<FakeGainedCouponModel.FakeUsedCouponsModel>,
    context: Context
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(ListBg)
            .padding(horizontal = 24.dp),
        contentPadding = PaddingValues(vertical = 18.dp)
    ) {
        items(coupons.size) { index ->
            Text(text = coupons[index].name)
        }
    }
}