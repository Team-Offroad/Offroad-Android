package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.AdaptationImage
import com.teamoffroad.core.designsystem.theme.BoxInfo
import com.teamoffroad.core.designsystem.theme.Gray400
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.NametagInactive
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.SettingSetting
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.core.designsystem.theme.Sub2
import com.teamoffroad.feature.explore.presentation.model.PlaceModel
import com.teamoffroad.offroad.feature.explore.R

@Composable
fun PlaceItem(
    modifier: Modifier = Modifier,
    placeModel: PlaceModel,
) {
    Column(modifier) {
        Row {
            PlaceTagItem(
                text = placeModel.placeCategory.krName,
                textColor = Sub2,
                backgroundColor = NametagInactive,
            )
            PlaceTagItem(
                modifier = Modifier.padding(start = 6.dp),
                text = placeModel.placeArea,
                textColor = Sub,
                backgroundColor = NametagInactive,
            )
        }
        Text(
            text = placeModel.name,
            style = OffroadTheme.typography.tooltipTitle,
            color = Main2,
            modifier = Modifier.padding(top = 12.dp)
        )
        Text(
            text = placeModel.address,
            style = OffroadTheme.typography.hint,
            color = Gray400,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun PlaceExtraItem(
    placeModel: PlaceModel,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 14.dp)
            .background(color = BoxInfo, shape = RoundedCornerShape(4.dp))
            .padding(vertical = 6.dp, horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AdaptationImage(
            imageUrl = placeModel.categoryImageUrl,
            modifier = Modifier
                .height(18.dp)
                .widthIn(max = 24.dp),
            contentScale = ContentScale.FillHeight,
        )
        Text(
            text = placeModel.shortIntroduction,
            color = Main2,
            style = OffroadTheme.typography.textContents,
            modifier = Modifier
                .padding(start = 6.dp)
                .padding(vertical = 2.dp)
                .weight(1f),
        )
        if (placeModel.isVisited) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                VerticalDivider(
                    color = SettingSetting,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 6.dp)
                        .requiredHeight(20.dp),
                )
                Text(
                    text = stringResource(R.string.explore_explore_count, placeModel.visitCount),
                    color = Sub2,
                    style = OffroadTheme.typography.tooltipNumber,
                    maxLines = 1,
                )
            }
        }
    }
}

@Composable
private fun PlaceTagItem(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color,
    backgroundColor: Color,
) {
    Text(
        text = text,
        style = OffroadTheme.typography.textContentsSmall,
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 10.dp, vertical = 6.dp),
        color = textColor
    )
}
