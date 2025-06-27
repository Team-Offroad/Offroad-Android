package com.teamoffroad.feature.explore.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.core.common.domain.usecase.GetCompleteQuestListUseCase
import com.teamoffroad.feature.explore.domain.model.Quest
import com.teamoffroad.feature.explore.domain.usecase.GetQuestListUseCase
import com.teamoffroad.feature.explore.presentation.model.QuestUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestViewModel
    @Inject
    constructor(
        private val getQuestListUseCase: GetQuestListUseCase,
        private val getCompleteQuestListUseCase: GetCompleteQuestListUseCase,
    ) : ViewModel() {
        private val _uiState: MutableStateFlow<QuestUiState> = MutableStateFlow(QuestUiState())
        val uiState: StateFlow<QuestUiState> = _uiState.asStateFlow()

        private val _completeQuests = MutableStateFlow<List<String>>(emptyList())
        val completeQuests = _completeQuests.asStateFlow()

        init {
            updateQuests(true)
            updateQuests(false)
        }

        fun loadCompleteQuests() {
            viewModelScope.launch {
                runCatching {
                    getCompleteQuestListUseCase()
                }.onSuccess { quests ->
                    _completeQuests.value = quests
                }
            }
        }

        fun updateProceedingToggle() {
            _uiState.value =
                uiState.value.copy(
                    isProceedingQuest = !uiState.value.isProceedingQuest,
                )
        }

        fun updateQuests(isProceeding: Boolean = uiState.value.isProceedingQuest) {
            if (uiState.value.isAdditionalLoading) return
            val cursorId = getCursorId(isProceeding)
            if (cursorId == uiState.value.lastUpdatedCursorId) return

            viewModelScope.launch {
                updateLoadingState(cursorId)

                runCatching {
                    getQuestListUseCase(isProceeding, cursorId, 20)
                }.onSuccess { quests ->
                    _uiState.value = uiState.value.copy(lastUpdatedCursorId = cursorId)
                    when (quests.isEmpty()) {
                        true -> updateLoadableState(isProceeding)
                        false -> updateExistQuests(isProceeding, quests)
                    }
                }.onFailure {
                    _uiState.value =
                        uiState.value.copy(
                            isLoading = false,
                            isAdditionalLoading = false,
                            isError = true,
                        )
                }
            }
        }

        private fun updateLoadableState(isProceeding: Boolean) {
            _uiState.value =
                uiState.value.copy(
                    isLoadable =
                        when (isProceeding) {
                            true -> uiState.value.isLoadable.copy(first = false)
                            false -> uiState.value.isLoadable.copy(second = false)
                        },
                    isLoading = false,
                    isAdditionalLoading = false,
                )
        }

        private fun getCursorId(isProceeding: Boolean): Long =
            when (isProceeding) {
                true ->
                    uiState.value.proceedingQuests
                        .lastOrNull()
                        ?.cursorId ?: 0L

                false ->
                    uiState.value.totalQuests
                        .lastOrNull()
                        ?.cursorId ?: 0L
            }

        private fun updateLoadingState(cursorId: Long) {
            _uiState.value =
                when {
                    cursorId == 0L ->
                        uiState.value.copy(
                            isLoading = true,
                            isError = false,
                        )

                    else ->
                        uiState.value.copy(
                            isAdditionalLoading = true,
                            isError = false,
                        )
                }
        }

        private fun updateExistQuests(
            isProceeding: Boolean,
            quests: List<Quest>,
        ) {
            _uiState.value =
                when (isProceeding) {
                    true ->
                        uiState.value.copy(
                            proceedingQuests = uiState.value.proceedingQuests + quests,
                            isLoading = false,
                            isAdditionalLoading = false,
                        )

                    false ->
                        uiState.value.copy(
                            totalQuests = uiState.value.totalQuests + quests,
                            isLoading = false,
                            isAdditionalLoading = false,
                        )
                }
        }
    }
