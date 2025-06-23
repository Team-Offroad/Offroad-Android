package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.ExpandableItem
import com.teamoffroad.feature.explore.domain.model.Quest

@Composable
fun DefaultQuestItem(
    quest: Quest,
    isExpanded: Boolean,
    onExpandClick: () -> Unit,
) {
    ExpandableItem(
        isExpanded = isExpanded,
        onExpandClick = onExpandClick,
        defaultContent = {
            QuestItem(quest = quest)
        },
        extraContent = {
            QuestExtraItem(questModel = quest)
        },
        modifier = Modifier.padding(bottom = 14.dp),
    )
}
