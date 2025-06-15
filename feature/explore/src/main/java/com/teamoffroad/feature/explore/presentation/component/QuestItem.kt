package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.BoxInfo
import com.teamoffroad.core.designsystem.theme.Gray400
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub2
import com.teamoffroad.feature.explore.domain.model.Quest
import com.teamoffroad.offroad.feature.explore.R

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
fun QuestProgressText(questModel: Quest) {
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
