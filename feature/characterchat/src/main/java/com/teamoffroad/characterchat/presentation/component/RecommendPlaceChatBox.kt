package com.teamoffroad.characterchat.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.RecommendPlaceButtonGradient1
import com.teamoffroad.core.designsystem.theme.RecommendPlaceButtonGradient2
import com.teamoffroad.core.designsystem.theme.RecommendPlaceButtonGradient3
import com.teamoffroad.core.designsystem.theme.RecommendPlaceButtonGradient4
import com.teamoffroad.core.designsystem.theme.RecommendPlaceButtonGradient5
import com.teamoffroad.core.designsystem.theme.RecommendPlaceButtonGradient6
import com.teamoffroad.core.designsystem.theme.RecommendPlaceButtonGradient7
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
import com.teamoffroad.core.designsystem.theme.Sub4
import com.teamoffroad.offroad.feature.characterchat.R

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

val RecommendPlaceButtonGradient = listOf(
    RecommendPlaceButtonGradient1,
    RecommendPlaceButtonGradient2,
    RecommendPlaceButtonGradient3,
    RecommendPlaceButtonGradient4,
    RecommendPlaceButtonGradient5,
    RecommendPlaceButtonGradient6,
    RecommendPlaceButtonGradient7,
)

@Composable
fun RecommendPlaceChatBox(
    name: String = "",
    text: String,
    navigateToRecommendPlace: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 36.dp)
            .border(
                width = 1.dp,
                brush = Brush.horizontalGradient(colors = RecommendPlaceStrokeGradientColors),
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(12.dp)
            )
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
                Column {
                    Row(
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
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

                    Text(
                        text = stringResource(id = R.string.chat_orb_recommend_place),
                        color = Main1,
                        style = OffroadTheme.typography.textRegular,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                            .padding(horizontal = 16.dp)
                            .padding(top = 10.dp)
                            .border(
                                width = 1.dp,
                                brush = Brush.horizontalGradient(colors = RecommendPlaceStrokeGradientColors),
                                shape = RoundedCornerShape(4.dp)
                            )
                            .background(
                                brush = Brush.horizontalGradient(RecommendPlaceButtonGradient),
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(vertical = 10.dp)
                            .clickableWithoutRipple { navigateToRecommendPlace(true) }
                    )
                }

            }
        )
    }
}


