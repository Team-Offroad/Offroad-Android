package com.teamoffroad.feature.mypage.presentation.component.coupon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.offroad.feature.mypage.R

@Composable
internal fun AcquireCouponScreen(
    navigateToMyPage: () -> Unit,
    acquireCouponViewModel: AcquireCouponViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .background(ListBg)
            .fillMaxSize()
    ) {
        OffroadActionBar()
        NavigateBackAppBar(
            modifier = Modifier.padding(top = 20.dp).background(ListBg),
            text = stringResource(id = R.string.mypage_mypage)
        ) { navigateToMyPage() }
        Text(text = "acquire coupon")
    }
}

@Preview(showBackground = true)
@Composable
fun AcquireCouponScreenPreview() {
    OffroadTheme {
        AcquireCouponScreen(navigateToMyPage = { })
    }
}