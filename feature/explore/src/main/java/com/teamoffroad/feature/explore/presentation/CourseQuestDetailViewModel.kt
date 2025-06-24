package com.teamoffroad.feature.explore.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.explore.domain.usecase.GetQuestCourseUseCase
import com.teamoffroad.feature.explore.presentation.mapper.toUi
import com.teamoffroad.feature.explore.presentation.model.CourseQuestPlacesUiModel
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
        private val _quests: MutableStateFlow<CourseQuestPlacesUiModel> = MutableStateFlow(CourseQuestPlacesUiModel())
        val quests: StateFlow<CourseQuestPlacesUiModel> get() = _quests

        private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
        val isLoading: StateFlow<Boolean> get() = _isLoading

        private val _isError: MutableStateFlow<Boolean> = MutableStateFlow(false)
        val isError: StateFlow<Boolean> get() = _isError

        fun loadQuestDetails(questId: Long) {
            viewModelScope.launch {
                runCatching {
                    getQuestCourseUseCase(questId)
                }.onSuccess { places ->
                    _quests.value = CourseQuestPlacesUiModel(places.map { it.toUi() } + places.map { it.toUi() })
                    _isLoading.value = false
                    _isError.value = false
                }.onFailure {
                    _isLoading.value = false
                    _isError.value = true
                }
            }
        }
    }
