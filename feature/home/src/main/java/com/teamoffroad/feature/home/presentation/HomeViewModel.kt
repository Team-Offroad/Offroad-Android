package com.teamoffroad.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.home.domain.model.Emblem
import com.teamoffroad.feature.home.domain.model.UserQuests
import com.teamoffroad.feature.home.domain.model.UsersAdventuresInformation
import com.teamoffroad.feature.home.domain.repository.UserRepository
import com.teamoffroad.feature.home.presentation.component.UiState
import com.teamoffroad.feature.home.presentation.component.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _getUsersAdventuresInformationState =
        MutableStateFlow<UiState<UsersAdventuresInformation>>(
            UiState.Loading
        )
    val getUsersAdventuresInformationState = _getUsersAdventuresInformationState.asStateFlow()

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

    private val _circleProgressBar = MutableStateFlow(0f)
    val circleProgressBar = _circleProgressBar.asStateFlow()

    private val _linearProgressBar = MutableStateFlow(0f)
    val linearProgressBar = _linearProgressBar.asStateFlow()

    fun getUsersAdventuresInformation(category: String) {
        viewModelScope.launch {
            runCatching {
                userRepository.getUsersAdventuresInformation(category)
            }.onSuccess { state ->
                _getUsersAdventuresInformationState.emit(UiState.Success(state))
                updateSelectedEmblem(state.emblemName)
                updateCharacterImage(state.baseImageUrl)
                updateMotionImageUrl(state.motionImageUrl)
            }.onFailure { t ->
                val errorMessage = getErrorMessage(t)
                _getUsersAdventuresInformationState.emit(UiState.Failure(errorMessage))
            }
        }
    }

    private fun updateSelectedEmblem(emblemName: String) {
        _selectedEmblem.value = emblemName
    }

    private fun updateCharacterImage(imageUrl: String) {
        _baseCharacterImage.value = imageUrl
    }

    private fun updateMotionImageUrl(motionImageUrl: String?) {
        _motionCharacterUrl.value = motionImageUrl
    }

    fun updateCategory(category: String) {
        _category.value = category
    }

    fun updateCircleProgressBar(amount: Float, total: Float) {
        _circleProgressBar.value = if (total == 0f) 0f else amount / total
    }

    fun updateLinearProgressBar(amount: Float, total: Float) {
        _linearProgressBar.value = if (total == 0f) 0f else amount / total
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