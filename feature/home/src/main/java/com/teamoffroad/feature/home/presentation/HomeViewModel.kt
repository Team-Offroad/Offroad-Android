package com.teamoffroad.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.home.domain.model.Emblem
import com.teamoffroad.feature.home.domain.model.UserQuests
import com.teamoffroad.feature.home.domain.model.UsersAdventuresInformations
import com.teamoffroad.feature.home.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _getUsersAdventuresInformationsState = MutableStateFlow<UiState<UsersAdventuresInformations>>(UiState.Loading)
    val getUsersAdventuresInformationsState = _getUsersAdventuresInformationsState.asStateFlow()

    private val _selectedEmblem = MutableStateFlow("")
    val selectedEmblem = _selectedEmblem.asStateFlow()

    private val _baseCharacterImage = MutableStateFlow("")
    val baseCharacterImage = _baseCharacterImage.asStateFlow()

    private val _motionCharacterUrl: MutableStateFlow<String?> = MutableStateFlow(null)
    val motionCharacterUrl = _motionCharacterUrl.asStateFlow()

    private val _category = MutableStateFlow("NONE")
    val category = _category.asStateFlow()

    private val _getEmblemsState = MutableStateFlow<UiState<List<Emblem>>>(UiState.Loading)
    val getEmblemsState = _getEmblemsState.asStateFlow()

    private val _patchEmblemState = MutableStateFlow<UiState<Unit>>(UiState.Loading)
    val patchEmblemState = _patchEmblemState.asStateFlow()

    private val _getUserQuestsState = MutableStateFlow<UiState<UserQuests>>(UiState.Loading)
    val getUserQuestsState = _getUserQuestsState.asStateFlow()

    fun getUsersAdventuresInformations(category: String) {
        viewModelScope.launch {
            runCatching {
                userRepository.getUsersAdventuresInformations(category)
            }.onSuccess { state ->
                _getUsersAdventuresInformationsState.emit(UiState.Success(state))
                updateSelectedEmblem(state.emblemName)
                updateCharacterImage(state.baseImageUrl)
                updateMotionImageUrl(state.motionImageUrl)
            }.onFailure { t ->
                val errorMessage = getErrorMessage(t)
                _getUsersAdventuresInformationsState.emit(UiState.Failure(errorMessage))
            }
        }
    }

    fun updateSelectedEmblem(emblemName: String) {
        _selectedEmblem.value = emblemName
    }

    fun updateCharacterImage(imageUrl: String) {
        _baseCharacterImage.value = imageUrl
    }

    fun updateMotionImageUrl(motionImageUrl: String?) {
        _motionCharacterUrl.value = motionImageUrl
    }

    fun updateCategory(category: String) {
        _category.value = category
    }

    fun getEmblems() {
        viewModelScope.launch {
            runCatching {
                userRepository.getEmblems()
            }.onSuccess { state ->
                _getEmblemsState.emit(UiState.Success(state))
            }.onFailure { t ->
                val errorMessage = getErrorMessage(t)
                _getEmblemsState.emit(UiState.Failure(errorMessage))
            }
        }
    }

    fun patchEmblem(emblem: Emblem) {
        viewModelScope.launch {
            runCatching {
                userRepository.patchEmblem(emblem.emblemCode)
            }.onSuccess { state ->
                _patchEmblemState.emit(UiState.Success(state))
                updateSelectedEmblem(emblem.emblemName)
            }.onFailure { t ->
                val errorMessage = getErrorMessage(t)
                _patchEmblemState.emit(UiState.Failure(errorMessage))
            }
        }
    }

    fun getUserQuests() {
        viewModelScope.launch {
            runCatching {
                userRepository.getUserQuests()
            }.onSuccess { state ->
                _getUserQuestsState.emit(UiState.Success(state))
            }.onFailure { t ->
                val errorMessage = getErrorMessage(t)
                _getUserQuestsState.emit(UiState.Failure(errorMessage))
            }
        }
    }

}