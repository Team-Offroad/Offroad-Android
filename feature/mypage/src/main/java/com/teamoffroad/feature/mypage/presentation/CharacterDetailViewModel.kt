package com.teamoffroad.feature.mypage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.mypage.domain.usecase.GetCharacterDetailUseCase
import com.teamoffroad.feature.mypage.presentation.mapper.toUi
import com.teamoffroad.feature.mypage.presentation.model.CharacterDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterDetailUseCase: GetCharacterDetailUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<CharacterDetailUiState> = MutableStateFlow(CharacterDetailUiState())
    val uiState: StateFlow<CharacterDetailUiState> = _uiState.asStateFlow()

    fun updateCharacterDetail(characterId: Int) {
        viewModelScope.launch {
            runCatching {
                _uiState.value = uiState.value.copy(
                    isLoading = true,
                )
                characterDetailUseCase(characterId)
            }.onSuccess {
                _uiState.value = uiState.value.copy(
                    characterDetailModel = it.toUi(),
                )
            }.onFailure {
                _uiState.value = uiState.value.copy(
                    isLoading = false,
                    isError = true,
                )
            }
        }
    }
}
