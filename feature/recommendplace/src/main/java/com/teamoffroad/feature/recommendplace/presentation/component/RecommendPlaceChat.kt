package com.teamoffroad.feature.recommendplace.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub4
import com.teamoffroad.offroad.feature.recommendplace.R

@Composable
fun RecommendPlaceChat(
    name: String,
    text: String,
    time: String,
    onClose: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(RecommendPlaceFillGradientColors),
            )
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_recommend_place_close),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.End)
                .clickableWithoutRipple { onClose() }
        )
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {

            Text(
                text = name,
                style = OffroadTheme.typography.textBold,
                color = Sub4,
            )
            Text(
                text = ": ",
                color = Main2,
                style = OffroadTheme.typography.textRegular,
                modifier = Modifier.padding(start = 4.dp),
            )
            Text(
                text = text,
                style = OffroadTheme.typography.textRegular,
                color = Main2,
            )
        }
    }
}