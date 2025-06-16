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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.RecommendPlaceFillGradient1
import com.teamoffroad.core.designsystem.theme.RecommendPlaceFillGradient2
import com.teamoffroad.core.designsystem.theme.RecommendPlaceFillGradient3
import com.teamoffroad.core.designsystem.theme.RecommendPlaceFillGradient4
import com.teamoffroad.core.designsystem.theme.RecommendPlaceFillGradient5
import com.teamoffroad.core.designsystem.theme.RecommendPlaceFillGradient6
import com.teamoffroad.core.designsystem.theme.RecommendPlaceFillGradient7
import com.teamoffroad.core.designsystem.theme.RecommendPlaceStrokeGradient1
import com.teamoffroad.core.designsystem.theme.RecommendPlaceStrokeGradient2
import com.teamoffroad.core.designsystem.theme.RecommendPlaceStrokeGradient3
import com.teamoffroad.core.designsystem.theme.RecommendPlaceStrokeGradient4
import com.teamoffroad.core.designsystem.theme.RecommendPlaceStrokeGradient5
import com.teamoffroad.core.designsystem.theme.RecommendPlaceStrokeGradient6
import com.teamoffroad.core.designsystem.theme.RecommendPlaceStrokeGradient7
import com.teamoffroad.offroad.feature.recommendplace.R

val RecommendPlaceStrokeGradientColors = listOf(
    RecommendPlaceStrokeGradient1,
    RecommendPlaceStrokeGradient2,
    RecommendPlaceStrokeGradient3,
    RecommendPlaceStrokeGradient4,
    RecommendPlaceStrokeGradient5,
    RecommendPlaceStrokeGradient6,
    RecommendPlaceStrokeGradient7,
)

val RecommendPlaceFillGradientColors = listOf(
    RecommendPlaceFillGradient1,
    RecommendPlaceFillGradient2,
    RecommendPlaceFillGradient3,
    RecommendPlaceFillGradient4,
    RecommendPlaceFillGradient5,
    RecommendPlaceFillGradient6,
    RecommendPlaceFillGradient7,
)

@Composable
fun RecommendPlaceButton(
    hasChatted: Boolean,
    content: String,
    onClick: () -> Unit,
    navigateToOrderRecommendPlace: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                brush = Brush.horizontalGradient(colors = RecommendPlaceStrokeGradientColors),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(1.dp)
            .background(
                brush = Brush.horizontalGradient(RecommendPlaceFillGradientColors),
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
            content = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = content.ifEmpty { "반가워 나는 추천 오브 츄링이야!\n장소 추천이 필요해? 어디로 갈건지 말해봐 츄츄~" }, // 임의로 넣어둠
                        modifier = Modifier
                            .padding(horizontal = 26.dp, vertical = 20.dp),
                        style = OffroadTheme.typography.boxMedi,
                        lineHeight = 16.sp
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(R.drawable.ic_recommend_place_chat),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .clickableWithoutRipple { onClick() }
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