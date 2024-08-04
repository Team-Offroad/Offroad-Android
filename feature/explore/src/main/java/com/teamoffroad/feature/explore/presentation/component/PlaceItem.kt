package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.ExpandableItem
import com.teamoffroad.core.designsystem.theme.Gray400
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.NametagInactive
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.core.designsystem.theme.Sub2
import com.teamoffroad.offroad.feature.explore.R

@Composable
fun PlaceItem(
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Row {
            PlaceTagItem(
                text = "카페",
                textColor = Sub2,
                backgroundColor = NametagInactive,
            )
            PlaceTagItem(
                modifier = Modifier.padding(start = 6.dp),
                text = "시간이 머무는 마을",
                textColor = Sub,
                backgroundColor = NametagInactive,
            )
        }
        Text(
            text = "브릭루즈",
            style = OffroadTheme.typography.tooltipTitle,
            color = Main2,
            modifier = Modifier.padding(top = 12.dp)
        )
        Text(
            text = "경기도 파주시 지목로 143",
            style = OffroadTheme.typography.hint,
            color = Gray400,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun PlaceExtraItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 14.dp)
            .background(color = Sub2, shape = RoundedCornerShape(4.dp)) // TODO: 색상 변경
            .padding(vertical = 8.dp, horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_explore_location_overlay),
            contentDescription = null,
            modifier = Modifier.height(18.dp)
        )
        Text(
            text = "파주 브런치 맛집 대형 카페",
            color = Main2,
            style = OffroadTheme.typography.textContents,
            modifier = Modifier.padding(start = 6.dp)
        )
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

@Preview(showBackground = true)
@Composable
fun PlaceScreenPreview() {
    OffroadTheme {
        ExpandableItem(
            defaultContent = { PlaceItem() },
            extraContent = { PlaceExtraItem() }
        )
    }
}
