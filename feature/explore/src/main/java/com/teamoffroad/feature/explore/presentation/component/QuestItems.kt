package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.CircularLoadingAnimationLine
import com.teamoffroad.core.designsystem.component.LinearLoadingAnimation
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.feature.explore.domain.model.Quest

private const val NULL_INDEX = -1
private const val LOAD_THRESHOLD = 10

@Composable
fun QuestItems(
    quests: List<Quest>,
    updateQuests: () -> Unit,
    isProceeding: Boolean,
    isLoading: Boolean,
    isAdditionalLoading: Boolean,
    isLoadable: Boolean,
    onDetailClick: (Quest) -> Unit,
) {
    val listState = rememberLazyListState()
    var expandedIndex by remember { mutableIntStateOf(NULL_INDEX) }

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { index ->
                if (index + LOAD_THRESHOLD >= quests.size && isLoadable) {
                    updateQuests()
                }
            }
    }

    LaunchedEffect(quests) {
        if (expandedIndex == NULL_INDEX && quests.isNotEmpty()) {
            expandedIndex = quests.indexOfFirst { it.courseQuestInfo.isCourse }
        }
    }

    LaunchedEffect(isProceeding) {
        listState.scrollToItem(0)
    }

    LazyColumn(
        modifier =
            Modifier
                .fillMaxSize()
                .background(ListBg)
                .padding(horizontal = 24.dp),
        state = listState,
        contentPadding = PaddingValues(vertical = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement =
            when (isLoading) {
                true -> Arrangement.Center
                false -> Arrangement.Top
            },
    ) {
        item {
            LinearLoadingAnimation(isLoading = isLoading)
        }

        items(quests.size) { index ->
            val quest = quests[index]
            val isExpanded = expandedIndex == index

            val toggleExpand = {
                expandedIndex = if (isExpanded) NULL_INDEX else index
            }

            when (quest.courseQuestInfo.isCourse) {
                true ->
                    CourseQuestItem(
                        quest = quest,
                        onDetailClick = { onDetailClick(quest) },
                        isExpanded = isExpanded,
                        onExpandClick = toggleExpand,
                    )

                false ->
                    DefaultQuestItem(
                        quest = quest,
                        isExpanded = isExpanded,
                        onExpandClick = toggleExpand,
                    )
            }
        }
        item {
            CircularLoadingAnimationLine(isLoading = isAdditionalLoading)
        }
    }
}
