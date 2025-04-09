package com.teamoffroad.feature.recommendplace.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.offroad.feature.recommendplace.R

@Composable
fun RecommendPlaceButton() {
    Box(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                brush = Brush.horizontalGradient(colors = RecommendPlaceStrokeGradientColors),
                shape = RoundedCornerShape(14.dp)
            )
            .padding(1.dp)
            .background(
                brush = Brush.horizontalGradient(RecommendPlaceFillGradientColors),
                shape = RoundedCornerShape(14.dp)
            )
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
            content = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = stringResource(id = R.string.recommend_place_welcome),
                        modifier = Modifier
                            .padding(horizontal = 26.dp, vertical = 20.dp),
                        style = OffroadTheme.typography.boxMedi
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(R.drawable.ic_recommend_place_chat),
                        contentDescription = null,
                        modifier = Modifier.padding(end = 10.dp)
                    )
                    Image(
                        painter = painterResource(R.drawable.ic_recommend_place_arrow_right),
                        contentDescription = null,
                        modifier = Modifier.padding(end = 18.dp)
                    )
                }
            }
        )
    }

}