package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.BoxInfo
import com.teamoffroad.core.designsystem.theme.Gray400
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.explore.domain.model.Quest
import com.teamoffroad.offroad.feature.explore.R
import java.time.LocalDateTime

@Composable
fun QuestExtraItem(
    questModel: Quest,
    modifier: Modifier = Modifier,
) {
    Column {
        Text(
            text = questModel.description,
            style = OffroadTheme.typography.boxMedi,
            color = Gray400,
            maxLines = 2,
            modifier = Modifier.padding(top = 8.dp),
        )
        Column(
            modifier =
                modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp, bottom = 4.dp)
                    .background(color = BoxInfo, shape = RoundedCornerShape(9.dp))
                    .padding(vertical = 8.dp, horizontal = 10.dp),
        ) {
            QuestDetailItem(
                icon = painterResource(id = R.drawable.ic_explore_quest_task),
                text = questModel.requirement,
            )
            QuestDetailItem(
                icon = painterResource(id = R.drawable.ic_explore_quest_reward),
                text = questModel.reward,
                modifier = Modifier.padding(top = 4.dp),
            )
        }
    }
}

@Composable
private fun QuestDetailItem(
    icon: Painter,
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.width(22.dp),
            contentScale = ContentScale.FillWidth,
        )
        Text(
            text = text,
            style = OffroadTheme.typography.textContentsSmall,
            color = Main2,
            modifier = Modifier.padding(start = 6.dp),
        )
    }
}

@Preview
@Composable
fun QuestExtraItemPreview() {
    OffroadTheme {
        QuestExtraItem(
            questModel =
                Quest(
                    questId = 9830L,
                    questName = "도심 속 공원 탐방",
                    description = "연희동에서 카페를 탐방탐방 상세 정보 어쩌구 저쩌구 뭐",
                    requirement = "브릭루즈 방문",
                    reward = "포인트 1000원 적립",
                    cursorId = 2667L,
                    progress = Quest.QuestProgressModel(currentCount = 80, totalCount = 100),
                    courseQuestInfo = Quest.CourseQuestInfo(isCourse = true, deadline = LocalDateTime.now(), courseQuestPlaces = listOf()),
                ),
        )
    }
}
