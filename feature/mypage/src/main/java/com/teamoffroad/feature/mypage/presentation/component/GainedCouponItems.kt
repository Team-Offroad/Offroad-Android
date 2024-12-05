package com.teamoffroad.feature.mypage.presentation.component

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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.teamoffroad.core.designsystem.component.AdaptationImage
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.Black25
import com.teamoffroad.core.designsystem.theme.Black55
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Stroke
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.mypage.domain.model.UserAvailableCoupons
import com.teamoffroad.feature.mypage.domain.model.UserUsedCoupons
import com.teamoffroad.feature.mypage.presentation.GainedCouponViewModel.Companion.COUPON_SIZE
import com.teamoffroad.offroad.feature.mypage.R
import com.teamoffroad.offroad.feature.mypage.R.drawable

@Composable
fun AvailableCouponItems(
    availableCouponsCount: Int,
    coupons: List<UserAvailableCoupons.AvailableCoupons>,
    navigateToAvailableCouponDetail: (Int, String, String, String, Int) -> Unit,
    getUserCoupons: (Boolean, Int) -> Unit,
) {
    if (availableCouponsCount == 0) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_my_page_empty),
                contentDescription = "empty available coupon",
                modifier = Modifier
                    .padding(start = 110.dp, top = 132.dp, end = 110.dp)
                    .size(138.dp)
            )

            Text(
                text = stringResource(id = R.string.my_page_available_coupon_empty),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                style = OffroadTheme.typography.boxMedi.copy(lineHeight = with(LocalDensity.current) { 20.dp.toSp() }),
                color = Black55,
                textAlign = TextAlign.Center,
            )
        }
    } else {
        AvailableCouponGrid(
            coupons = coupons,
            getUserCoupons = getUserCoupons,
        ) { coupon ->
            AvailableCouponItem(coupon, navigateToAvailableCouponDetail)
        }
    }
}

@Composable
private fun AvailableCouponItem(
    coupon: UserAvailableCoupons.AvailableCoupons,
    navigateToAvailableCouponDetail: (Int, String, String, String, Int) -> Unit,
) {
    Box(
        modifier = Modifier
            .aspectRatio(148f / 176f)
            .border(1.dp, Stroke, RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .background(Main1)
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            .clickableWithoutRipple(interactionSource = remember { MutableInteractionSource() }) {
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
            AdaptationImage(
                imageUrl = coupon.couponImageUrl,
                modifier = Modifier
                    .aspectRatio(131f / 131f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(White)
                    .fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = coupon.name,
                    color = Main2,
                    style = OffroadTheme.typography.textContentsSmall,
                    textAlign = TextAlign.Center
                )
            }
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
    usedCouponsCount: Int,
    coupons: List<UserUsedCoupons.UsedCoupons>,
    getUserCoupons: (Boolean, Int) -> Unit,
) {
    if (usedCouponsCount == 0) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_my_page_empty),
                contentDescription = "empty used coupon",
                modifier = Modifier
                    .padding(start = 110.dp, top = 132.dp, end = 110.dp)
                    .size(138.dp)
            )
            Text(
                text = stringResource(id = R.string.my_page_used_coupon_empty),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                style = OffroadTheme.typography.boxMedi.copy(lineHeight = with(LocalDensity.current) { 20.dp.toSp() }),
                color = Black55,
                textAlign = TextAlign.Center,
            )
        }
    } else {
        UsedCouponGrid(coupons, getUserCoupons) { coupon ->
            UsedCouponItem(coupon)
        }
    }
}

@Composable
private fun UsedCouponItem(
    coupon: UserUsedCoupons.UsedCoupons,
) {
    Box(
        modifier = Modifier
            .aspectRatio(148f / 176f)
            .border(1.dp, Stroke.copy(alpha = 0.25f), RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .background(Main1)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AdaptationImage(
                imageUrl = coupon.couponImageUrl,
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                    .aspectRatio(131f / 131f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(White)
                    .fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = coupon.name,
                    color = Main2,
                    style = OffroadTheme.typography.textContentsSmall,
                    textAlign = TextAlign.Center
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Black25, RoundedCornerShape(10.dp))

        ) {
            GainedCouponLockedCover()
        }
    }
}

@Composable
fun AvailableCouponGrid(
    coupons: List<UserAvailableCoupons.AvailableCoupons>,
    getUserCoupons: (Boolean, Int) -> Unit,
    couponContent: @Composable (UserAvailableCoupons.AvailableCoupons) -> Unit,
) {
    val gridState = rememberLazyGridState()
    var showLottieLoading by remember { mutableStateOf(false) }

    LaunchedEffect(gridState, coupons) {
        snapshotFlow { gridState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                val lastVisibleItem = visibleItems.lastOrNull()
                if (lastVisibleItem != null && lastVisibleItem.index == coupons.size - 1 && !showLottieLoading) {
                    showLottieLoading = true
                }
            }
    }

    LazyVerticalGrid(
        state = gridState,
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
            couponContent(coupons[index])
        }

        if (coupons.size >= COUPON_SIZE) {
            if (showLottieLoading) {
                item(span = { GridItemSpan(2) }) {
                    LoadingIndicator {
                        val lastItem = coupons.lastOrNull()
                        if (lastItem != null) {
                            getUserCoupons(false, lastItem.cursorId)
                        }
                        showLottieLoading = false
                    }
                }
            }
        }
    }
}

@Composable
fun UsedCouponGrid(
    coupons: List<UserUsedCoupons.UsedCoupons>,
    getUserCoupons: (Boolean, Int) -> Unit,
    couponContent: @Composable (UserUsedCoupons.UsedCoupons) -> Unit,
) {
    val gridState = rememberLazyGridState()
    var showLottieLoading by remember { mutableStateOf(false) }

    LaunchedEffect(gridState, coupons) {
        snapshotFlow { gridState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                val lastVisibleItem = visibleItems.lastOrNull()
                if (lastVisibleItem != null && lastVisibleItem.index == coupons.size - 1 && !showLottieLoading) {
                    showLottieLoading = true
                }
            }
    }

    LazyVerticalGrid(
        state = gridState,
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
            couponContent(coupons[index])
        }

        if (coupons.size >= COUPON_SIZE) {
            if (showLottieLoading) {
                item(span = { GridItemSpan(2) }) {
                    LoadingIndicator {
                        val lastItem = coupons.lastOrNull()
                        if (lastItem != null) {
                            getUserCoupons(false, lastItem.cursorId)
                        }
                        showLottieLoading = false
                    }
                }
            }
        }
    }
}

@Composable
private fun LoadingIndicator(onAnimationEnd: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .size(38.dp),
        contentAlignment = Alignment.Center
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(com.teamoffroad.offroad.core.designsystem.R.raw.loading_circle))
        val animationState = animateLottieCompositionAsState(composition, iterations = 1)

        if (animationState.isAtEnd && animationState.isPlaying) {
            LaunchedEffect(Unit) { onAnimationEnd() }
        }

        LottieAnimation(composition, animationState.progress)
    }
}

@Composable
private fun GainedCouponLockedCover() {
    Image(
        modifier = Modifier
            .fillMaxSize()
            .padding(60.dp)
            .padding(bottom = 36.dp),
        painter = painterResource(id = drawable.ic_my_page_coupon_locked),
        contentDescription = "locked",
        alignment = Alignment.Center
    )
}
