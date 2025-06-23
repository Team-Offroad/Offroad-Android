package com.teamoffroad.feature.explore.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.explore.domain.model.CourseQuestPlace
import com.teamoffroad.feature.explore.domain.usecase.GetQuestCourseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseQuestDetailViewModel
    @Inject
    constructor(
        private val getQuestCourseUseCase: GetQuestCourseUseCase,
    ) : ViewModel() {
        private val _places: MutableStateFlow<List<CourseQuestPlace>> = MutableStateFlow(emptyList())
        val places: StateFlow<List<CourseQuestPlace>> get() = _places

        private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
        val isLoading: StateFlow<Boolean> get() = _isLoading

        private val _isError: MutableStateFlow<Boolean> = MutableStateFlow(false)
        val isError: StateFlow<Boolean> get() = _isError

        fun loadQuestDetails(questId: Long) {
            viewModelScope.launch {
                runCatching {
                    getQuestCourseUseCase(questId)
                }.onSuccess { places ->
                    _places.value = places
                    _isLoading.value = false
                    _isError.value = false
                }.onFailure {
                    _isLoading.value = false
                    _isError.value = true
                }
            }
        }
    }
