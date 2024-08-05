package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.core.designsystem.theme.Gray400
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.NametagInactive
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.core.designsystem.theme.Sub2
import com.teamoffroad.feature.explore.presentation.model.FakePlaceModel

@Composable
fun PlaceItem(
    modifier: Modifier = Modifier,
    placeModel: FakePlaceModel,
) {
    Column(modifier) {
        Row {
            PlaceTagItem(
                text = placeModel.placeCategory.krTitle,
                textColor = Sub2,
                backgroundColor = NametagInactive,
            )
            PlaceTagItem(
                modifier = Modifier.padding(start = 6.dp),
                text = placeModel.town,
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
    placeModel: FakePlaceModel,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 14.dp)
            .background(color = Gray100, shape = RoundedCornerShape(4.dp)) // TODO: 색상 변경
            .padding(vertical = 6.dp, horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(placeModel.categoryImageUrl)
                .decoderFactory(SvgDecoder.Factory())
                .build(),
            contentDescription = null,
            modifier = Modifier
                .height(18.dp),
            contentScale = ContentScale.FillHeight,
        )
        Text(
            text = placeModel.shortIntroduction,
            color = Main2,
            style = OffroadTheme.typography.textContents,
            modifier = Modifier
                .padding(start = 6.dp)
                .padding(vertical = 2.dp)
        )
        if (placeModel.visitCount > 0) {
            VerticalDivider(
                color = Sub2, // TODO: 색상 변경
                thickness = 2.dp,
                modifier = Modifier
                    .padding(start = 8.dp, end = 6.dp)
                    .requiredHeight(20.dp)
            )
            Text(
                text = "탐험횟수: ${placeModel.visitCount}",
                color = Sub2,
                style = OffroadTheme.typography.tooltipNumber,
            )
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
