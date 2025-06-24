package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.AdaptationImage
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.BoxInfo
import com.teamoffroad.core.designsystem.theme.Gray300
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.NametagInactive
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.core.designsystem.theme.Sub2
import com.teamoffroad.core.designsystem.theme.Transparent
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.explore.domain.model.Location
import com.teamoffroad.feature.explore.presentation.model.CourseQuestPlaceUiModel
import com.teamoffroad.feature.explore.presentation.model.PlaceCategory
import com.teamoffroad.offroad.feature.explore.R

@Composable
fun CourseQuestPlaceItem(
    quest: CourseQuestPlaceUiModel,
    isFirst: Boolean = false,
    isLast: Boolean = false,
    onVisitClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var rowHeight by remember { mutableIntStateOf(0) }

    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .background(Main1)
                .padding(horizontal = 24.dp)
                .onGloballyPositioned { layoutCoordinates ->
                    rowHeight = layoutCoordinates.size.height
                },
    ) {
        Column(
            modifier =
                Modifier
                    .height(IntrinsicSize.Min)
                    .width(22.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val lineHeight =
                with(LocalDensity.current) {
                    (rowHeight / 2).toDp() - 22.dp / 2
                }

            if (!isFirst) {
                DashedLine(
                    modifier =
                        Modifier
                            .height(lineHeight.coerceAtLeast(0.dp))
                            .width(1.dp),
                )
            } else {
                Spacer(modifier = Modifier.height(lineHeight.coerceAtLeast(0.dp)))
            }

            CategoryMarker(category = quest.category, size = 22.dp)

            if (!isLast) {
                DashedLine(
                    modifier =
                        Modifier
                            .height(lineHeight.coerceAtLeast(0.dp))
                            .width(1.dp),
                )
            } else {
                Spacer(modifier = Modifier.height(lineHeight.coerceAtLeast(0.dp)))
            }
        }

        Spacer(modifier = Modifier.width(10.dp))

        Row(
            modifier =
                modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .background(color = White, shape = RoundedCornerShape(12.dp))
                    .padding(start = 12.dp, top = 12.dp, bottom = 10.dp),
        ) {
            AdaptationImage(
                imageUrl = quest.categoryImage,
                modifier =
                    Modifier
                        .size(66.dp)
                        .background(color = Transparent, shape = RoundedCornerShape(4.dp)),
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = quest.category.krName,
                    style = OffroadTheme.typography.textContentsSmall,
                    color = Sub2,
                    modifier =
                        Modifier
                            .background(color = NametagInactive, shape = RoundedCornerShape(12.dp))
                            .padding(horizontal = 8.dp, vertical = 2.dp),
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = quest.name, style = OffroadTheme.typography.textBold)
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = quest.address, style = OffroadTheme.typography.textContentsSmall)
            }

            if (quest.isVisited) {
                Image(
                    painter = painterResource(R.drawable.img_explore_quest_clear),
                    contentDescription = null,
                    modifier =
                        Modifier
                            .padding(end = 4.dp)
                            .size(68.dp),
                )
            } else {
                Text(
                    text = "방문",
                    style = OffroadTheme.typography.btnSmall,
                    color = Sub,
                    modifier =
                        Modifier
                            .padding(end = 14.dp)
                            .align(Alignment.CenterVertically)
                            .background(color = BoxInfo, shape = RoundedCornerShape(4.dp))
                            .clickableWithoutRipple { onVisitClick() }
                            .padding(horizontal = 14.dp, vertical = 4.dp),
                )
            }
        }
    }
}

@Composable
private fun DashedLine(
    modifier: Modifier = Modifier,
    color: androidx.compose.ui.graphics.Color = Gray300,
    dashHeight: Dp = 4.dp,
    gapHeight: Dp = 4.dp,
) {
    Canvas(modifier = modifier) {
        val totalHeight = size.height
        val dashHeightPx = dashHeight.toPx()
        val gapHeightPx = gapHeight.toPx()
        var y = 0f
        while (y < totalHeight) {
            drawLine(
                color = color,
                start = Offset(x = size.width / 2f, y = y),
                end = Offset(x = size.width / 2f, y = y + dashHeightPx),
                strokeWidth = size.width,
            )
            y += dashHeightPx + gapHeightPx
        }
    }
}

@Preview
@Composable
fun CourseQuestPlaceItemPreview() {
    OffroadTheme {
        CourseQuestPlaceItem(
            quest =
                CourseQuestPlaceUiModel(
                    category = PlaceCategory.CAFFE,
                    name = "Nima",
                    address = "Evonne",
                    position = Location(latitude = 16.877, longitude = 79.676),
                    isVisited = true,
                    categoryImage = "Miguel",
                    description = "Ignacio",
                    placeId = 0L,
                ),
            onVisitClick = {},
        )
    }
}
