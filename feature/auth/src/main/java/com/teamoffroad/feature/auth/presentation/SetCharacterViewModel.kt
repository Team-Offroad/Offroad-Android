package com.teamoffroad.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.auth.domain.model.Character
import com.teamoffroad.feature.auth.domain.usecase.GetCharacterListUseCase
import com.teamoffroad.feature.auth.domain.usecase.SetCharacterUseCase
import com.teamoffroad.feature.auth.presentation.model.SetCharacterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetCharacterViewModel @Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase,
    private val setCharacterUseCase: SetCharacterUseCase,
) : ViewModel() {

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters.asStateFlow()

    private val _selectedCharacter = MutableStateFlow<Character>(Character(0, "", "", "", ""))
    val selectedCharacter: StateFlow<Character> = _selectedCharacter.asStateFlow()

    private val _uiState = MutableStateFlow<SetCharacterUiState>(SetCharacterUiState.Loading)
    val uiState: StateFlow<SetCharacterUiState> = _uiState.asStateFlow()

    fun getCharacters() {
        viewModelScope.launch {
            runCatching {
                getCharacterListUseCase.invoke()
            }.onSuccess { characters ->
                _characters.value = characters
                updateSelectedCharacter(characters.first().id)
            }.onFailure {
                _uiState.value = SetCharacterUiState.Error
            }
        }
    }

    fun updateCharacter(characterId: Int) {
        viewModelScope.launch {
            runCatching {
                setCharacterUseCase.invoke(characterId)
            }.onSuccess { characterImgUrl ->
                updateSelectedCharacter(characterId)
                _uiState.value = SetCharacterUiState.Success(characterImgUrl)
            }.onFailure {
                _uiState.value = SetCharacterUiState.Error
            }
        }
    }

    fun updateSelectedCharacter(characterId: Int) {
        _selectedCharacter.value = characters.value.find {
            it.id == characterId
        } ?: Character(0, "", "", "", "")
    }
}
