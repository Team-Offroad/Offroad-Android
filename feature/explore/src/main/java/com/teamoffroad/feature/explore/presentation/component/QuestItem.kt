package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Gray400
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub2
import com.teamoffroad.feature.explore.domain.model.Quest
import com.teamoffroad.offroad.feature.explore.R
import java.time.LocalDateTime

@Composable
fun QuestItem(
    quest: Quest,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = quest.questName,
            style = OffroadTheme.typography.textBold,
            color = Main2,
            modifier = Modifier.weight(1f),
        )
        when (quest.progress.isCompleted) {
            true ->
                Text(
                    text = stringResource(R.string.explore_quest_completed),
                    style = OffroadTheme.typography.questCompleted,
                    color = Sub2,
                    modifier = Modifier.offset(x = 14.dp),
                )

            false -> QuestProgressText(quest)
        }
    }
}

@Composable
private fun QuestProgressText(questModel: Quest) {
    val progressText = "(${questModel.progress.currentCount}/${questModel.progress.totalCount})"
    val annotatedString =
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = Gray400)) {
                append(stringResource(R.string.explore_quest_achievement_rate))
            }
            withStyle(style = SpanStyle(color = Sub2)) {
                append(progressText)
            }
        }

    Text(
        text = annotatedString,
        style = OffroadTheme.typography.hint,
        modifier = Modifier.offset(x = 14.dp),
    )
}

@Preview
@Composable
fun QuestItemPreview() {
    OffroadTheme {
        QuestItem(
            quest =
                Quest(
                    questId = 9830L,
                    questName = "도심 속 공원 탐방",
                    description = "연희동에서 카페를 탐방탐방 상세 정보 어쩌구 저쩌구 뭐",
                    requirement = "브릭루즈 방문",
                    reward = "포인트 1000원 적립",
                    cursorId = 2667L,
                    progress = Quest.QuestProgressModel(currentCount = 80, totalCount = 100),
                    courseQuestInfo = Quest.CourseQuestInfo(isCourse = false, deadline = LocalDateTime.now(), courseQuestPlaces = listOf()),
                ),
        )
    }
}
