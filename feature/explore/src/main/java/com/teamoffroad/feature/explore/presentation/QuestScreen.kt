package com.teamoffroad.feature.explore.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.actionBarPadding
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.feature.explore.presentation.component.QuestHeader
import com.teamoffroad.feature.explore.presentation.component.QuestItems
import com.teamoffroad.offroad.feature.explore.R

@Composable
fun QuestScreen(
    navigateToBack: () -> Unit,
    questViewModel: QuestViewModel = hiltViewModel(),
) {
    val uiState = questViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .navigationPadding()
            .background(color = Main1)
            .actionBarPadding()
    ) {
        NavigateBackAppBar(
            text = stringResource(id = R.string.explore_explore),
            modifier = Modifier.padding(top = 20.dp)
        ) { navigateToBack() }
        QuestHeader(
            uiState.value.isProceedingQuest,
            questViewModel::updateProceedingToggle,
        )
        QuestItems(
            quests = when (uiState.value.isProceedingQuest) {
                true -> uiState.value.proceedingQuests
                false -> uiState.value.totalQuests
            },
            updateQuests = {
                questViewModel.updateQuests()
            },
            isLoading = uiState.value.isLoading,
            isAdditionalLoading = uiState.value.isAdditionalLoading,
            isLoadable = when (uiState.value.isProceedingQuest) {
                true -> uiState.value.isLoadable.first
                false -> uiState.value.isLoadable.second
            },
        )
    }
}
