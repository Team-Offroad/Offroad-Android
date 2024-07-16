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
    private val _getEmblemsState = MutableStateFlow<UiState<List<Emblem>>>(UiState.Loading)
    val getEmblemsState = _getEmblemsState.asStateFlow()

    private val _patchEmblemState = MutableStateFlow<UiState<Unit>>(UiState.Loading)
    val patchEmblemState = _patchEmblemState.asStateFlow()

    val token =
        "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJpYXQiOjE3MjExMjI3ODcsImV4cCI6MTcyMTEyNDU4NywibWVtYmVySWQiOjV9.FWSrNqzHUB4TLNz7suKa9xz9ttrZLec_AxodgU8y0_QQp4PD-7oKUZwuVPVPrgcS\""

    fun getEmblems() {
        viewModelScope.launch {
            runCatching {
                emblemRepository.getEmblems(
                    token
                )
            }.onSuccess { state ->
                _getEmblemsState.emit(UiState.Success(state))
            }.onFailure { t ->
                _getEmblemsState.emit(UiState.Failure(t.message.toString()))
            }
        }
    }

    fun patchEmblem(emblem: Emblem) {
        viewModelScope.launch {
            runCatching {
                emblemRepository.patchEmblem(emblem.emblemCode, token)
            }.onSuccess { state ->
                _patchEmblemState.emit(UiState.Success(state))
            }.onFailure { t ->
                _patchEmblemState.emit(UiState.Failure(t.message.toString()))
            }
        }
    }

}