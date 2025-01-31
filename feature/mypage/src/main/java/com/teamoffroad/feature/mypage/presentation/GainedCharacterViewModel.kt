package com.teamoffroad.feature.mypage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.mypage.domain.usecase.GetCharacterListUseCase
import com.teamoffroad.feature.mypage.presentation.mapper.toUi
import com.teamoffroad.feature.mypage.presentation.model.GainedCharacterUiState
import com.teamoffroad.feature.mypage.presentation.model.GainedCharacterUiState.Error
import com.teamoffroad.feature.mypage.presentation.model.GainedCharacterUiState.Loading
import com.teamoffroad.feature.mypage.presentation.model.GainedCharacterUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GainedCharacterViewModel @Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<GainedCharacterUiState> = MutableStateFlow(Loading)
    val uiState: StateFlow<GainedCharacterUiState> = _uiState.asStateFlow()

    fun updateCharacters() {
        viewModelScope.launch {
            runCatching {
                _uiState.value = Loading
                getCharacterListUseCase()
            }.onSuccess {
                _uiState.value = Success(
                    it.map { characters -> characters.toUi() }
                )
            }.onFailure {
                _uiState.value = Error(it.message ?: "Unknown error")
            }
        }
    }
}
