package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.ExpandableItem
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.feature.explore.presentation.model.QuestModel

@Composable
fun QuestItems(
    quests: List<QuestModel>,
    updateQuests: () -> Unit,
    isLoading: Boolean,
    isLoadable: Boolean,
) {
    var expandedIndex by remember { mutableIntStateOf(NULL_INDEX) }
    val listState = rememberLazyListState()

    LaunchedEffect(listState, isLoadable) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { index ->
                if (index + LOAD_THRESHOLD >= quests.size && isLoadable) {
                    updateQuests()
                }
            }
    }

    LaunchedEffect(quests) {
        listState.scrollToItem(0)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(ListBg)
            .padding(horizontal = 24.dp),
        state = listState,
        contentPadding = PaddingValues(vertical = 18.dp),
    ) {
        items(quests.size) { index ->
            ExpandableItem(
                isExpanded = expandedIndex == index,
                onExpandClick = {
                    expandedIndex = if (expandedIndex == index) NULL_INDEX else index
                },
                defaultContent = {
                    QuestItem(questModel = quests[index])
                },
                extraContent = {
                    QuestExtraItem(questModel = quests[index])
                },
                modifier = Modifier.padding(bottom = 14.dp)
            )
        }
    }
}

private const val NULL_INDEX = -1
private const val LOAD_THRESHOLD = 10
