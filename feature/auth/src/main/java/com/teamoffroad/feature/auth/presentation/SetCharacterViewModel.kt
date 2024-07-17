package com.teamoffroad.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.auth.domain.model.Character
import com.teamoffroad.feature.auth.domain.usecase.GetCharacterListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetCharacterViewModel @Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase,
) : ViewModel() {

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters.asStateFlow()

    private val _selectedCharacter = MutableStateFlow<Character>(Character(0, "", "", "", ""))
    val selectedCharacter: StateFlow<Character> = _selectedCharacter.asStateFlow()

    fun getCharacters() {
        viewModelScope.launch {
            runCatching {
                getCharacterListUseCase.invoke()
            }.onSuccess { characters ->
                _characters.value = characters
                updateSelectedCharacter(characters.first().id)
            }.onFailure {

            }
        }
    }

    fun updateSelectedCharacter(characterId: Int) {
        _selectedCharacter.value = characters.value.find {
            it.id == characterId
        } ?: Character(0, "", "", "", "")
    }
}
