package com.teamoffroad.feature.explore.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.core.common.domain.tracker.Tracker
import com.teamoffroad.feature.explore.domain.model.Location
import com.teamoffroad.feature.explore.domain.usecase.GetQuestCourseUseCase
import com.teamoffroad.feature.explore.domain.usecase.PostExploreLocationAuthUseCase
import com.teamoffroad.feature.explore.presentation.mapper.toUi
import com.teamoffroad.feature.explore.presentation.model.CourseQuestPlaceUiModel
import com.teamoffroad.feature.explore.presentation.model.CourseQuestPlacesUiModel
import com.teamoffroad.feature.explore.presentation.model.ExploreAuthState
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
        private val postExploreLocationAuthUseCase: PostExploreLocationAuthUseCase,
        private val tracker: Tracker,
    ) : ViewModel() {
        private val _quests: MutableStateFlow<CourseQuestPlacesUiModel> = MutableStateFlow(CourseQuestPlacesUiModel())
        val quests: StateFlow<CourseQuestPlacesUiModel> get() = _quests

        private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
        val isLoading: StateFlow<Boolean> get() = _isLoading

        private val _isError: MutableStateFlow<Boolean> = MutableStateFlow(false)
        val isError: StateFlow<Boolean> get() = _isError

        private val _exploreAuthState: MutableStateFlow<ExploreAuthState> = MutableStateFlow(ExploreAuthState.None)
        val exploreAuthState: StateFlow<ExploreAuthState> get() = _exploreAuthState

        private val _location: MutableStateFlow<Location> = MutableStateFlow(Location())
        val location: StateFlow<Location> get() = _location

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

        fun performExplore(
            place: CourseQuestPlaceUiModel,
            latitude: Double = _location.value.latitude,
            longitude: Double = _location.value.longitude,
        ) {
            viewModelScope.launch {
                runCatching {
                    postExploreLocationAuthUseCase(place.placeId, latitude, longitude)
                }.onSuccess { exploreResult ->
                    when {
                        !exploreResult.isValidPosition -> {
                            _exploreAuthState.value =
                                ExploreAuthState.LocationError(
                                    exploreResult.successCharacterImageUrl,
                                )
                        }

                        !exploreResult.isFirstVisitToday -> {
                            _exploreAuthState.value =
                                ExploreAuthState.DuplicateError(
                                    exploreResult.successCharacterImageUrl,
                                )
                        }

                        else -> {
                            _exploreAuthState.value =
                                ExploreAuthState.Success(
                                    place.category,
                                    exploreResult.successCharacterImageUrl,
                                    exploreResult.completeQuests,
                                )
                            tracker.trackEvent("explore_success", mapOf("place_id" to place.placeId))
                            if (exploreResult.completeQuests.isNotEmpty()) {
                                tracker.trackEvent(
                                    "quest_success",
                                    mapOf("quests" to exploreResult.completeQuests),
                                )
                            }
                        }
                    }
                }.onFailure {
                    _exploreAuthState.value = ExploreAuthState.EtcError
                }
            }
        }

        fun updateLocation(
            latitude: Double,
            longitude: Double,
        ) {
            _location.value = Location(latitude, longitude)
        }
    }
