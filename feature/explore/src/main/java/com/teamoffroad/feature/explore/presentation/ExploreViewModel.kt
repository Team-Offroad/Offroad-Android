package com.teamoffroad.feature.explore.presentation

import androidx.lifecycle.ViewModel
import com.naver.maps.geometry.LatLng
import com.teamoffroad.feature.explore.presentation.model.ExploreUiState
import com.teamoffroad.feature.explore.presentation.model.PlaceCategory
import com.teamoffroad.feature.explore.presentation.model.PlaceModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState: MutableStateFlow<ExploreUiState> = MutableStateFlow(ExploreUiState())
    val uiState: StateFlow<ExploreUiState> get() = _uiState

    fun updatePermission(isAlreadyHavePermission: Boolean, isPermissionRejected: Boolean) {
        _uiState.value = uiState.value.copy(
            isAlreadyHavePermission = isAlreadyHavePermission,
            isPermissionRejected = isPermissionRejected
        )
    }

    fun updateLocation(latitude: Double, longitude: Double) {
        _uiState.value = uiState.value.copy(
            locationModel = uiState.value.locationModel.updateLocation(latitude, longitude)
        )
    }

    fun updateTrackingToggle(isUserTrackingEnabled: Boolean) {
        _uiState.value = uiState.value.copy(
            locationModel = uiState.value.locationModel.updateTrackingToggle(isUserTrackingEnabled)
        )
    }

    fun updatePlaces() {
        _uiState.value = uiState.value.copy(
            places = dummyPlaces,
            loading = false,
        )
    }

    private val dummyPlaces = listOf(
        PlaceModel(
            id = 1,
            name = "Place1",
            address = "서울시 강남구 테헤란로 123",
            shortIntroduction = "테헤란로의 멋진 카페",
            placeCategory = PlaceCategory.CAFFE,
            location = LatLng(37.566900, 126.923500),
            visitCount = 5
        ),
        PlaceModel(
            id = 2,
            name = "Place2",
            address = "서울시 서초구 서초대로 456",
            shortIntroduction = "서초대로의 유명 레스토랑",
            placeCategory = PlaceCategory.RESTAURANT,
            location = LatLng(37.567000, 126.923600),
            visitCount = 8
        ),
        PlaceModel(
            id = 3,
            name = "Place3",
            address = "서울시 마포구 홍익로 789",
            shortIntroduction = "홍익로의 아름다운 공원",
            placeCategory = PlaceCategory.PARK,
            location = LatLng(37.566800, 126.923400),
            visitCount = 3
        ),
        PlaceModel(
            id = 4,
            name = "Place4",
            address = "서울시 종로구 세종대로 101",
            shortIntroduction = "세종대로의 문화 명소",
            placeCategory = PlaceCategory.CULTURE,
            location = LatLng(37.566700, 126.923200),
            visitCount = 12
        ),
        PlaceModel(
            id = 5,
            name = "Place5",
            address = "서울시 송파구 올림픽로 222",
            shortIntroduction = "올림픽로의 스포츠 센터",
            placeCategory = PlaceCategory.SPORT,
            location = LatLng(37.566600, 126.923100),
            visitCount = 20
        ),
        PlaceModel(
            id = 6,
            name = "Place6",
            address = "서울시 중구 을지로 333",
            shortIntroduction = "을지로의 무명 장소",
            placeCategory = PlaceCategory.NONE,
            location = LatLng(37.566500, 126.923000),
            visitCount = 0
        ),
    )
}
