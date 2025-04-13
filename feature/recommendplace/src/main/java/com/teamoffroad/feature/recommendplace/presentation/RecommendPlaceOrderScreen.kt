package com.teamoffroad.feature.recommendplace.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.teamoffroad.core.designsystem.theme.Black25
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.recommendplace.presentation.component.PlaceType
import com.teamoffroad.feature.recommendplace.presentation.component.RecommendPlaceSelect
import com.teamoffroad.offroad.feature.recommendplace.R

@Composable
fun RecommendPlaceOrder(
    navigateToBack: () -> Unit,
) {
    var selectedType by remember { mutableStateOf<PlaceType?>(null) }

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
            RecommendPlaceSelect(
                selectedType = selectedType,
                onSelectType = { selectedType = it }
            )
            RecommendPlaceOrderButton(
                isEnabled = selectedType != null,
                submitOrder = { // 제출
                    // 다 선택했는지 확인
                    Log.d("orb submit", "ok")
                }
            )
        }
    }
}

@Composable
fun RecommendPlaceOrderButton(
    isEnabled: Boolean,
    submitOrder: () -> Unit
) {
    Text(
        text = stringResource(id = R.string.recommend_place_order_question_button),
        color = White,
        style = OffroadTheme.typography.textRegular,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 24.dp)
            .then(
                if (isEnabled) Modifier.border(
                    width = 1.dp,
                    shape = RoundedCornerShape(12.dp),
                    color = Main2
                ) else Modifier
            )
            .background(
                shape = RoundedCornerShape(6.dp),
                color = if (isEnabled) Main2 else Black25
            )
            .padding(horizontal = 110.dp, 14.dp)
            .clickableWithoutRipple(enabled = isEnabled) { submitOrder() }
    )
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