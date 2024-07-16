package com.teamoffroad.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.home.domain.model.Emblem
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
    private val _state = MutableStateFlow<UiState<List<Emblem>>>(UiState.Loading)
    val state = _state.asStateFlow()

    init {
        getEmblems()
    }

    fun getEmblems() {
        viewModelScope.launch {
            runCatching {
                emblemRepository.getEmblems(
                    "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJpYXQiOjE3MjExMTcyNjUsImV4cCI6MTcyMTExOTA2NSwibWVtYmVySWQiOjN9.UJqIw9-EWxPFFLN6afh9WDQLskPbRhiTRotncuxIsy3jKjIUMHONTymgc5KhQHU9"
                )
            }.onSuccess { state ->
                _state.emit(UiState.Success(state))
            }.onFailure { t ->
                _state.emit(UiState.Failure(t.message.toString()))
            }
        }
    }



}