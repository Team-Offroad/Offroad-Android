package com.teamoffroad.feature.recommendplace.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.explore.domain.usecase.GetPreviousLocationUseCase
import com.teamoffroad.feature.explore.presentation.model.PlaceCategory
import com.teamoffroad.feature.recommendplace.domain.repository.PlaceRecommendationsRepository
import com.teamoffroad.feature.recommendplace.presentation.model.PlaceRecommendationsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendPlaceViewModel @Inject constructor(
    private val placeRecommendationsRepository: PlaceRecommendationsRepository,
    private val getPreviousLocationUseCase: GetPreviousLocationUseCase,
) : ViewModel() {
    private val _placeRecommendationsUiState = MutableStateFlow(PlaceRecommendationsUiState())
    val placeRecommendationsUiState = _placeRecommendationsUiState.asStateFlow()

    fun getPlaceRecommendations() {
        if (placeRecommendationsUiState.value.isAdditionalLoading || placeRecommendationsUiState.value.isLoadable.not()) return

        viewModelScope.launch {
            runCatching {
                _placeRecommendationsUiState.value = _placeRecommendationsUiState.value.copy(
                    isAdditionalLoading = true,
                    isError = false,
                )
                placeRecommendationsRepository.fetchPlaceRecommendations()
            }.onSuccess { place ->
                _placeRecommendationsUiState.value = _placeRecommendationsUiState.value.copy(
                    isError = false,
                    isAdditionalLoading = false,
                    isLoadable = place.recommendations.isEmpty().not(),
                    isLoading = false,
                    recommendations = place.recommendations.map {
                        PlaceRecommendationsUiState.RecommendationsUiState(
                            id = it.id,
                            recommendationType = it.recommendationType,
                            name = it.name,
                            address = it.address,
                            shortIntroduction = it.shortIntroduction,
                            placeCategory = PlaceCategory.entries.find { cat -> cat.name == it.placeCategory }
                                ?: PlaceCategory.NONE,
                            placeArea = it.placeArea,
                            latitude = it.latitude,
                            longitude = it.longitude,
                            categoryImageUrl = it.categoryImageUrl
                        )
                    }
                )
            }.onFailure { t ->
                // 실제 부분
//                _placeRecommendationsUiState.value = _placeRecommendationsUiState.value.copy(
//                    isLoading = false,
//                    isAdditionalLoading = false,
//                    isError = true,
//                    errorMessage = t.message.toString(),
//                    recommendations = emptyList()
//                )

                // 테스트용 추천 항목 추가 -> 추후 삭제할 부분
                val testRecommendation = PlaceRecommendationsUiState.RecommendationsUiState(
                    id = 1,
                    recommendationType = "RESTAURANT,CAFE",
                    name = "테스트 장소",
                    address = "서울시 강남구 테스트로 123",
                    shortIntroduction = "테스트 장소입니다.",
                    placeCategory = PlaceCategory.RESTAURANT,
                    placeArea = "SEOUL",
                    latitude = 37.123456,
                    longitude = 127.123456,
                    categoryImageUrl = "https://test.com/test.jpg"
                )
                _placeRecommendationsUiState.value = _placeRecommendationsUiState.value.copy(
                    isError = false,
                    isAdditionalLoading = false,
                    isLoading = false,
                    recommendations = emptyList<PlaceRecommendationsUiState.RecommendationsUiState>().plus(testRecommendation)
                )
            }
        }
    }

}