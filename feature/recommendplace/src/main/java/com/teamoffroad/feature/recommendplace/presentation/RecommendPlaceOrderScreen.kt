package com.teamoffroad.feature.recommendplace.presentation

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.actionBarPadding
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.Gray300
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.Main3
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.feature.recommendplace.presentation.component.RecommendPlaceSelect
import com.teamoffroad.offroad.feature.recommendplace.R

@Composable
fun RecommendPlaceOrder(
    navigateToBack: () -> Unit,
) {
    Column(
        modifier = Modifier
            .navigationPadding()
            .background(color = Main1)
            .fillMaxSize()
            .actionBarPadding()
    ) {
        Column {
            RecommendPlaceOrderHeader(navigateToBack)
            HorizontalDivider(color = Gray100)
            RecommendPlaceSelect()
        }
    }
}

@Composable
fun RecommendPlaceOrderHeader(
    navigateToBack: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Image(
            painter = painterResource(id = com.teamoffroad.offroad.core.designsystem.R.drawable.ic_navigate_back),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Main2),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 6.dp)
                .size(48.dp)
                .padding(12.dp)
                .clickableWithoutRipple { navigateToBack() },
        )

        Text(
            text = stringResource(id = R.string.recommend_place_order),
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center),
            style = OffroadTheme.typography.bothLogin,
            color = Main2
        )
    }
}