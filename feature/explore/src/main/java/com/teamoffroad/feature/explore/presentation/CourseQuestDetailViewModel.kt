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
        private val _places: MutableStateFlow<CourseQuestPlacesUiModel> = MutableStateFlow(CourseQuestPlacesUiModel())
        val places: StateFlow<CourseQuestPlacesUiModel> get() = _places

        private val _isQuestLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
        val isQuestLoading: StateFlow<Boolean> get() = _isQuestLoading

        private val _isNetworkError: MutableStateFlow<Boolean> = MutableStateFlow(false)
        val isNetworkError: StateFlow<Boolean> get() = _isNetworkError

        private val _exploreAuthState: MutableStateFlow<ExploreAuthState> = MutableStateFlow(ExploreAuthState.None)
        val exploreAuthState: StateFlow<ExploreAuthState> get() = _exploreAuthState

        private val _location: MutableStateFlow<Location> = MutableStateFlow(Location())
        val location: StateFlow<Location> get() = _location

        fun loadQuestDetails(questId: Long) {
            viewModelScope.launch {
                runCatching {
                    getQuestCourseUseCase(questId)
                }.onSuccess { places ->
                    _places.value = CourseQuestPlacesUiModel(places.map { it.toUi() } + places.map { it.toUi() })
                    _isQuestLoading.value = false
                    _isNetworkError.value = false
                }.onFailure {
                    _isQuestLoading.value = false
                    _isNetworkError.value = true
                }
            }
        }

        fun performExplore(
            place: CourseQuestPlaceUiModel,
            latitude: Double = location.value.latitude,
            longitude: Double = location.value.longitude,
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
                            _places.value =
                                places.value.copy(
                                    places =
                                        places.value.places.map {
                                            if (it.placeId == place.placeId) {
                                                it.copy(isVisited = true)
                                            } else {
                                                it
                                            }
                                        },
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

        fun updateExploreAuthState(state: ExploreAuthState) {
            _exploreAuthState.value = state
        }
    }
