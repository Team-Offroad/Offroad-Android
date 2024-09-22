package com.teamoffroad.feature.mypage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.auth.domain.usecase.UpdateCharacterUseCase
import com.teamoffroad.feature.mypage.domain.usecase.GetCharacterDetailUseCase
import com.teamoffroad.feature.mypage.domain.usecase.GetCharacterMotionListUseCase
import com.teamoffroad.feature.mypage.presentation.mapper.toUi
import com.teamoffroad.feature.mypage.presentation.model.CharacterDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterDetailUseCase: GetCharacterDetailUseCase,
    private val characterMotionListUseCase: GetCharacterMotionListUseCase,
    private val updateCharacterUseCase: UpdateCharacterUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<CharacterDetailUiState> = MutableStateFlow(CharacterDetailUiState())
    val uiState: StateFlow<CharacterDetailUiState> = _uiState.asStateFlow()

    fun updateCharacterDetail(characterId: Int, isRepresentative: Boolean) {
        viewModelScope.launch {
            runCatching {
                _uiState.value = uiState.value.copy(
                    isLoading = true,
                )
                characterDetailUseCase(characterId)
            }.onSuccess {
                _uiState.value = uiState.value.copy(
                    characterDetailModel = it.toUi(isRepresentative),
                )
                updateCharacterMotionList(characterId)
            }.onFailure {
                _uiState.value = uiState.value.copy(
                    isLoading = false,
                    isError = true,
                )
            }
        }
    }

    private fun updateCharacterMotionList(characterId: Int) {
        viewModelScope.launch {
            runCatching {
                characterMotionListUseCase(characterId)
            }.onSuccess {
                _uiState.value = uiState.value.copy(
                    characterMotions = it.map { motion -> motion.toUi() }.toImmutableList(),
                    isLoading = false,
                )
            }.onFailure {
                _uiState.value = uiState.value.copy(
                    isLoading = false,
                    isError = true,
                )
            }
        }
    }

    fun updateIsRepresentative() {
        viewModelScope.launch {
            runCatching {
                updateCharacterUseCase(uiState.value.characterDetailModel.characterId)
            }.onSuccess {
                _uiState.value = uiState.value.copy(
                    characterDetailModel = uiState.value.characterDetailModel.copy(
                        isRepresentative = true,
                    ),
                    isRepresentativeUpdateSuccess = true,
                )
            }
        }
    }
}
