package com.teamoffroad.feature.explore.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun updateProceedingToggle() {
        _uiState.value = uiState.value.copy(
            isProceedingToggle = !uiState.value.isProceedingToggle,
        )
    }

    fun updateQuests(isProceeding: Boolean) {
        viewModelScope.launch {
            runCatching {
                _uiState.value = uiState.value.copy(
                    loading = true,
                    error = false,
                )
                getQuestListUseCase(isProceeding)
            }.onSuccess { quests ->
                _uiState.value = when {
                    isProceeding -> uiState.value.copy(
                        proceedingQuests = quests.map { it.toUi() },
                        loading = false,
                    )

                    !isProceeding -> uiState.value.copy(
                        totalQuests = quests.map { it.toUi() },
                        loading = false,
                    )

                    else -> uiState.value
                }
                if (!isProceeding) updateQuests(true)
            }.onFailure {
                _uiState.value = uiState.value.copy(
                    loading = false,
                    error = true,
                )
            }
        }
    }
}
