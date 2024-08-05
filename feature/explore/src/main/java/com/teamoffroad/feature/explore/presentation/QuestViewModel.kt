package com.teamoffroad.feature.explore.presentation

import androidx.lifecycle.ViewModel
import com.teamoffroad.feature.explore.presentation.model.FakeQuestModel
import com.teamoffroad.feature.explore.presentation.model.QuestUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class QuestViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState: MutableStateFlow<QuestUiState> = MutableStateFlow(QuestUiState())
    val uiState: StateFlow<QuestUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = QuestUiState(
            quests = FakeQuestModel.dummyQuests,
            loading = false,
            error = false,
        )
    }
}