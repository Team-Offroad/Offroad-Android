package com.teamoffroad.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.home.domain.model.Emblems
import com.teamoffroad.feature.home.domain.repository.EmblemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val emblemRepository: EmblemRepository
) : ViewModel() {
    private val _state = MutableStateFlow<UiState<Emblems>>(UiState.Loading)
    val state = _state.asStateFlow()

    init {
        getEmblems()
    }

    fun getEmblems() {
        viewModelScope.launch {
            runCatching {
                emblemRepository.getEmblems(
                    "Bearer "
                )
            }.onSuccess { state ->
                _state.emit(UiState.Success(state))
            }.onFailure { t ->
                _state.emit(UiState.Failure(t.message.toString()))
            }
        }
    }



}