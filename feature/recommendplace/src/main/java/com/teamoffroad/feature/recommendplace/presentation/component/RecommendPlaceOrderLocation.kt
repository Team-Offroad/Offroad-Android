package com.teamoffroad.feature.recommendplace.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.offroad.feature.recommendplace.R

@Composable
fun RecommendPlaceOrderLocation() {
    Column(
        modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 34.dp)
    ) {
        Text(
            text = stringResource(id = R.string.recommend_place_order_question_location),
            style = OffroadTheme.typography.textBold,
            color = Main2
        )
    }
}