package com.teamoffroad.feature.mypage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.mypage.domain.usecase.GetUserEmblemListUseCase
import com.teamoffroad.feature.mypage.presentation.model.GainedEmblemsResult
import com.teamoffroad.feature.mypage.presentation.model.GainedEmblemsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GainedEmblemsViewModel @Inject constructor(
    private val getUserEmblemListUseCase: GetUserEmblemListUseCase,
) : ViewModel() {

    private val _emblemsUiState: MutableStateFlow<GainedEmblemsUiState> =
        MutableStateFlow(GainedEmblemsUiState())
    val emblemsUiState: StateFlow<GainedEmblemsUiState> = _emblemsUiState.asStateFlow()

    private val _loadMoreEmblemsUiState: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val loadMoreEmblemsUiState: StateFlow<Boolean> = _loadMoreEmblemsUiState.asStateFlow()

    private var currentCount = 18

    fun getEmblems() {
        viewModelScope.launch {
            runCatching {
                getUserEmblemListUseCase()
            }.onSuccess { result ->
                val emblems =
                    result.getOrNull()?.take(currentCount)?.toImmutableList() ?: persistentListOf()
                _emblemsUiState.value = _emblemsUiState.value.copy(
                    emblemList = emblems,
                    gainedEmblemsValidateResult = GainedEmblemsResult.Success,
                )
            }.onFailure {
                _emblemsUiState.value =
                    _emblemsUiState.value.copy(gainedEmblemsValidateResult = GainedEmblemsResult.Error)
            }
        }
    }

    fun loadMoreEmblems() {
        viewModelScope.launch {
            if (_loadMoreEmblemsUiState.value) return@launch
            _loadMoreEmblemsUiState.value = true
            runCatching {
                getUserEmblemListUseCase()
            }.onSuccess { result ->
                val allEmblems = result.getOrNull() ?: persistentListOf()
                val newItems = allEmblems.drop(currentCount).take(10).toImmutableList()

                _emblemsUiState.value = _emblemsUiState.value.copy(
                    emblemList = (_emblemsUiState.value.emblemList + newItems).toImmutableList()
                )
                currentCount += 10
            }.onFailure {
            }.also {
                _loadMoreEmblemsUiState.value = false
            }
        }
    }
}
