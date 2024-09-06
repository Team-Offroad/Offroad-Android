package com.teamoffroad.feature.mypage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.mypage.domain.usecase.UserEmblemsUseCase
import com.teamoffroad.feature.mypage.presentation.model.GainedEmblemsResult
import com.teamoffroad.feature.mypage.presentation.model.GainedEmblemsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GainedEmblemsViewModel @Inject constructor(
    private val gainedEmblemUserCase: UserEmblemsUseCase
) : ViewModel() {

    private val _emblemsUiState: MutableStateFlow<GainedEmblemsUiState> =
        MutableStateFlow(GainedEmblemsUiState())
    val emblemsUiState: StateFlow<GainedEmblemsUiState> = _emblemsUiState.asStateFlow()

    fun getEmblems() {
        viewModelScope.launch {
            runCatching {
                gainedEmblemUserCase.invoke()
            }.onSuccess {
                _emblemsUiState.value = GainedEmblemsUiState(
                    it.getOrNull()!!.toImmutableList(),
                    GainedEmblemsResult.Success
                )
            }.onFailure {
                _emblemsUiState.value.copy(nicknameValidateResult = GainedEmblemsResult.Error)
            }
        }
    }
}