package com.teamoffroad.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.home.domain.model.Emblem
import com.teamoffroad.feature.home.domain.model.UserQuests
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

    private val _getUserQuestsState = MutableStateFlow<UiState<UserQuests>>(UiState.Loading)
    val getUserQuestsState = _getUserQuestsState.asStateFlow()

    val token =
        "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJpYXQiOjE3MjExMzM0MjYsImV4cCI6MTcyMTEzNTIyNiwibWVtYmVySWQiOjN9.PTVJdYg7s_etL3Vpn1B9DOPcBsqfga5LmQv4bM33kExlW_pt696gV50_h4is6PCN"

    fun getEmblems() {
        viewModelScope.launch {
            runCatching {
                emblemRepository.getEmblems(token)
            }.onSuccess { state ->
                _state.emit(UiState.Success(state))
            }.onFailure { t ->
                _state.emit(UiState.Failure(t.message.toString()))
            }
        }
    }

    fun getUserQuests() {
        viewModelScope.launch {
            runCatching {
                emblemRepository.getUserQuests(token)
            }.onSuccess { state ->
                _getUserQuestsState.emit(UiState.Success(state))
            }.onFailure { t ->
                _getUserQuestsState.emit(UiState.Failure(t.message.toString()))
            }
        }
    }

}