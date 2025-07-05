package com.teamoffroad.feature.explore.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.actionBarPadding
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.feature.explore.presentation.component.ExploreResultDialog
import com.teamoffroad.feature.explore.presentation.component.QuestHeader
import com.teamoffroad.feature.explore.presentation.component.QuestItems
import com.teamoffroad.offroad.feature.explore.R

@Composable
fun QuestScreen(
    navigateToQuestDetail: (questId: Long, deadline: String, dDay: Int) -> Unit,
    navigateToBack: () -> Unit,
    questViewModel: QuestViewModel = hiltViewModel(),
) {
    val uiState = questViewModel.uiState.collectAsStateWithLifecycle()
    val completeQuests = questViewModel.completeQuests.collectAsStateWithLifecycle()
    val isCompleteQuestDialogShown = remember { mutableStateOf(false) }
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer =
            LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_RESUME) {
                    questViewModel.loadCompleteQuests()
                }
            }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(completeQuests.value) {
        if (completeQuests.value.isNotEmpty()) {
            isCompleteQuestDialogShown.value = true
        }
    }

    Column(
        modifier =
            Modifier
                .navigationPadding()
                .background(color = Main1)
                .actionBarPadding(),
    ) {
        NavigateBackAppBar(
            text = stringResource(id = R.string.explore_explore),
            modifier = Modifier.padding(top = 20.dp),
        ) { navigateToBack() }
        QuestHeader(
            uiState.value.isProceedingQuest,
            questViewModel::updateProceedingToggle,
        )
        when (uiState.value.isProceedingQuest) {
            true ->
                QuestItems(
                    quests = uiState.value.proceedingQuests,
                    updateQuests = {
                        questViewModel.updateQuests()
                    },
                    isProceeding = uiState.value.isProceedingQuest,
                    isLoading = uiState.value.isLoading,
                    isAdditionalLoading = uiState.value.isAdditionalLoading,
                    isLoadable = uiState.value.isLoadable.first,
                    onDetailClick = { quest ->
                        navigateToQuestDetail(quest.questId, quest.courseQuestInfo.deadline.toString(), quest.getLeftDayCount())
                    },
                )

            false ->
                QuestItems(
                    quests = uiState.value.totalQuests,
                    updateQuests = {
                        questViewModel.updateQuests()
                    },
                    isProceeding = uiState.value.isProceedingQuest,
                    isLoading = uiState.value.isLoading,
                    isAdditionalLoading = uiState.value.isAdditionalLoading,
                    isLoadable = uiState.value.isLoadable.second,
                    onDetailClick = { quest ->
                        navigateToQuestDetail(quest.questId, quest.courseQuestInfo.deadline.toString(), quest.getLeftDayCount())
                    },
                )
        }
    }

    if (isCompleteQuestDialogShown.value && completeQuests.value.isNotEmpty()) {
        ExploreResultDialog(
            title = "퀘스트 성공 !",
            description = "퀘스트 **‘${completeQuests.value.first()}’ 외 ${completeQuests.value.size - 1}개**를\n클리어했어요! 마이페이지에서\n보상을 확인해보세요.",
            onDismissRequest = { isCompleteQuestDialogShown.value = false },
        )
    }
}
