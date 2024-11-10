package com.teamoffroad.feature.mypage.presentation.component

import android.content.Context
import android.util.Log
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.Black25
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Stroke
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.mypage.domain.model.UserCoupons
import com.teamoffroad.offroad.core.designsystem.R
import com.teamoffroad.offroad.feature.mypage.R.drawable
import kotlinx.coroutines.delay

@Composable
fun AvailableCouponItems(
    coupons: List<UserCoupons>,
    navigateToAvailableCouponDetail: (Int, String, String, String, Int) -> Unit,
    getUserCoupons: (Boolean, Int) -> Unit,
    isLoading: Boolean
) {
    val gridState = rememberLazyGridState()
    var isProcessing by remember { mutableStateOf(false) } // 중복 호출 방지 플래그
    var showLoading by remember { mutableStateOf(false) }

    LaunchedEffect(gridState, coupons) {
        snapshotFlow { gridState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                val lastVisibleItem = visibleItems.lastOrNull()
                val totalItemCount = coupons.size

                if (lastVisibleItem != null &&
                    lastVisibleItem.index == totalItemCount - 1 &&
                    !isProcessing && !showLoading
                ) {
                    showLoading = true
                    isProcessing = true

                    delay(2000)
                    val lastItem = coupons.lastOrNull()
                    if (lastItem != null) {
                        getUserCoupons(false, lastItem.cursorId)
                    }
                    showLoading = false
                    isProcessing = false
                }
            }
    }

    LazyVerticalGrid(
        state = gridState,
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .background(ListBg)
            .padding(horizontal = 24.dp)
            .fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 18.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(coupons.size) { index ->
            AvailableCouponItem(coupons[index], navigateToAvailableCouponDetail)
        }

        item {
            if (showLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(50.dp)
                        .padding(bottom = 16.dp)
                ) {
                    if (showLoading) {
                        val composition by rememberLottieComposition(
                            LottieCompositionSpec.RawRes(R.raw.loading_circle)
                        )
                        LottieAnimation(
                            composition,
                            iterations = LottieConstants.IterateForever
                        )
                    }
                }
            }

        }
    }
}


@Composable
fun AvailableCouponItem(
    coupon: UserCoupons,
    navigateToAvailableCouponDetail: (Int, String, String, String, Int) -> Unit,
) {
    Box(
        modifier = Modifier
            .aspectRatio(148f / 176f)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(12.dp),
                color = Stroke
            )
            .clip(shape = RoundedCornerShape(12.dp))
            .background(Main1)
            .padding(start = 8.dp, top = 8.dp, end = 8.dp)
            .clickableWithoutRipple(interactionSource = remember {
                MutableInteractionSource()
            }) {
                navigateToAvailableCouponDetail(
                    coupon.id,
                    coupon.name,
                    coupon.couponImageUrl,
                    coupon.description,
                    coupon.cursorId
                )
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
                    .aspectRatio(131f / 131f)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .background(White)
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

        if (coupon.isNewGained) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                Image(
                    painter = painterResource(id = drawable.img_coupon_new),
                    contentDescription = "new coupon",
                    modifier = Modifier
                        .padding(top = 16.dp, end = 16.dp)
                        .size(24.dp)
                )
            }
        }
    }
}

@Composable
fun UsedCouponItems(
    coupons: List<UserCoupons>,
    context: Context,
    getUserCoupons: (Boolean, Int) -> Unit
) {
    val gridState = rememberLazyGridState()

    LaunchedEffect(gridState) {
        snapshotFlow { gridState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                val lastVisibleItem = visibleItems.lastOrNull()
                if (lastVisibleItem != null && lastVisibleItem.index == coupons.size - 1) {
                    Log.d("UsedCouponItems", "Last item reached")
                    val lastItem = coupons.lastOrNull()
                    lastItem?.let { getUserCoupons(true, it.cursorId) }
                }
            }
    }

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
    coupon: UserCoupons,
    context: Context,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(148f / 176f)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(12.dp),
                color = Stroke.copy(alpha = 0.25f)
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
                    .aspectRatio(131f / 131f)
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .background(White),
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
            painter = painterResource(id = drawable.ic_my_page_coupon_locked),
            contentDescription = "locked",
            alignment = Alignment.Center,
        )
    }
}