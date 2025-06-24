package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.AdaptationImage
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.BoxInfo
import com.teamoffroad.core.designsystem.theme.NametagInactive
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.core.designsystem.theme.Sub2
import com.teamoffroad.core.designsystem.theme.Transparent
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.explore.presentation.model.CourseQuestPlaceUiModel
import com.teamoffroad.offroad.feature.explore.R

@Composable
fun CourseQuestPlaceItem(
    quest: CourseQuestPlaceUiModel,
    onVisitClick: () -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(vertical = 6.dp)
                .background(White, shape = RoundedCornerShape(12.dp))
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
