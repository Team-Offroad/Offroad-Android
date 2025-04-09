package com.teamoffroad.feature.recommendplace.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
fun RecommendPlaceHeader(

) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 28.dp, bottom = 22.dp)
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = stringResource(id = R.string.recommend_place_orb),
                style = OffroadTheme.typography.title
            )
            Image(
                painter = painterResource(id = R.drawable.ic_recommend_place),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 6.dp)
                    .size(24.dp),
            )
        }
    }
}

