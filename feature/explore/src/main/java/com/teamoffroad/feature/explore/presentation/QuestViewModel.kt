package com.teamoffroad.feature.explore.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.explore.domain.model.Quest
import com.teamoffroad.feature.explore.domain.usecase.GetQuestListUseCase
import com.teamoffroad.feature.explore.presentation.mapper.toUi
import com.teamoffroad.feature.explore.presentation.model.QuestUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestViewModel @Inject constructor(
    private val getQuestListUseCase: GetQuestListUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<QuestUiState> = MutableStateFlow(QuestUiState())
    val uiState: StateFlow<QuestUiState> = _uiState.asStateFlow()

    init {
        updateQuests(true)
        updateQuests(false)
    }

    fun updateProceedingToggle() {
        _uiState.value = uiState.value.copy(
            isProceedingQuest = !uiState.value.isProceedingQuest,
        )
    }

    fun updateQuests(isProceeding: Boolean) {
        if (uiState.value.isAdditionalLoading) return
        viewModelScope.launch {
            runCatching {
                val cursorId = getCursorId(isProceeding)
                updateLoadingState(cursorId)
                getQuestListUseCase(isProceeding, cursorId, 20)
            }.onSuccess { quests ->
                when (quests.isEmpty()) {
                    true -> updateLoadableState(isProceeding)
                    false -> updateExistQuests(isProceeding, quests)
                }
            }.onFailure {
                _uiState.value = uiState.value.copy(
                    isLoading = false,
                    isAdditionalLoading = false,
                    isError = true,
                )
            }
        }
    }

    private fun updateLoadableState(isProceeding: Boolean) {
        _uiState.value = uiState.value.copy(
            isLoadable = when (isProceeding) {
                true -> uiState.value.isLoadable.copy(first = false)
                false -> uiState.value.isLoadable.copy(second = false)
            },
            isLoading = false,
            isAdditionalLoading = false,
        )
    }

    private fun getCursorId(isProceeding: Boolean): Int {
        return when (isProceeding) {
            true -> uiState.value.proceedingQuests.lastOrNull()?.cursorId ?: 0
            false -> uiState.value.totalQuests.lastOrNull()?.cursorId ?: 0
        }
    }

    private fun updateLoadingState(cursorId: Int) {
        _uiState.value = when {
            cursorId == 0 -> uiState.value.copy(
                isLoading = true,
                isError = false,
            )

            else -> uiState.value.copy(
                isAdditionalLoading = true,
                isError = false,
            )
        }
    }

    private fun updateExistQuests(isProceeding: Boolean, quests: List<Quest>) {
        val updatedQuests = quests.map { it.toUi() }
        _uiState.value = when (isProceeding) {
            true -> uiState.value.copy(
                proceedingQuests = uiState.value.proceedingQuests + updatedQuests,
                isLoading = false,
                isAdditionalLoading = false,
            )

            false -> uiState.value.copy(
                totalQuests = uiState.value.totalQuests + updatedQuests,
                isLoading = false,
                isAdditionalLoading = false,
            )
        }
    }
}
