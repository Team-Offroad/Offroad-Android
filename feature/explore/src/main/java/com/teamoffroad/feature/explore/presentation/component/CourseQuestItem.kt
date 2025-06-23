package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.ExpandableItem
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub4_80
import com.teamoffroad.feature.explore.domain.model.Quest
import com.teamoffroad.offroad.feature.explore.R
import java.time.LocalDateTime

@Composable
fun CourseQuestItem(
    quest: Quest,
    isExpanded: Boolean,
    onExpandClick: () -> Unit,
    onDetailClick: (Long) -> Unit,
) {
    Box {
        ExpandableItem(
            isExpanded = isExpanded,
            onExpandClick = onExpandClick,
            onExtraContentClick = { onDetailClick(quest.questId) },
            defaultContent = {
                QuestItem(quest = quest)
            },
            extraContent = {
                QuestExtraItem(questModel = quest)
            },
            modifier = Modifier.padding(vertical = 14.dp),
        )
        if (quest.courseQuestInfo.isCourse) {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.ic_left_date_count),
                    contentDescription = null,
                )
                Text(
                    "D-${quest.getLeftDayCount()}",
                    style = OffroadTheme.typography.textBold,
                    color = Sub4_80,
                    modifier =
                        Modifier
                            .padding(bottom = 10.dp)
                            .align(Alignment.Center),
                )
            }
        }
    }
}

@Preview
@Composable
fun CourseQuestItemPreview() {
    OffroadTheme {
        CourseQuestItem(
            quest =
                Quest(
                    questId = 9830L,
                    questName = "도심 속 공원 탐방",
                    description = "연희동에서 카페를 탐방탐방 상세 정보 어쩌구 저쩌구 뭐",
                    requirement = "브릭루즈 방문",
                    reward = "포인트 1000원 적립",
                    cursorId = 2667L,
                    progress = Quest.QuestProgressModel(currentCount = 80, totalCount = 100),
                    courseQuestInfo =
                        Quest.CourseQuestInfo(
                            isCourse = true,
                            deadline = LocalDateTime.now(),
                            courseQuestPlaces = listOf(),
                        ),
                ),
            isExpanded = true,
            onExpandClick = {},
            onDetailClick = {},
        )
    }
}
