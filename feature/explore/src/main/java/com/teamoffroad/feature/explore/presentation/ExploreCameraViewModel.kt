package com.teamoffroad.feature.explore.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.explore.domain.usecase.PostExploreQrAuthUseCase
import com.teamoffroad.feature.explore.presentation.model.ExploreAuthState
import com.teamoffroad.feature.explore.presentation.model.PlaceCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreCameraViewModel @Inject constructor(
    private val postExploreQrAuthUseCase: PostExploreQrAuthUseCase,
) : ViewModel() {

    private val _exploreAuthState: MutableStateFlow<ExploreAuthState> = MutableStateFlow(ExploreAuthState.None)
    val exploreAuthState: StateFlow<ExploreAuthState> = _exploreAuthState.asStateFlow()

    private val _resultImageUrl: MutableStateFlow<String> = MutableStateFlow("")
    val resultImageUrl: StateFlow<String> = _resultImageUrl.asStateFlow()

    fun postExploreResult(placeId: Long, latitude: Double, longitude: Double, qr: String) {
        if (exploreAuthState.value is ExploreAuthState.Loading) return
        _exploreAuthState.value = ExploreAuthState.Loading

        viewModelScope.launch {
            runCatching {
                postExploreQrAuthUseCase.invoke(placeId, qr, latitude, longitude)
            }.onSuccess { exploreResult ->
                when (exploreResult.isQRMatched) {
                    true -> {
                        _resultImageUrl.value = exploreResult.characterImageUrl
                        _exploreAuthState.value = ExploreAuthState.Success(PlaceCategory.NONE)
                    }

                    false -> {
                        _resultImageUrl.value = exploreResult.characterImageUrl
                        _exploreAuthState.value = ExploreAuthState.CodeError
                    }
                }
            }.onFailure {
                when (it.message) {
                    "HTTP 400" -> _exploreAuthState.value = ExploreAuthState.LocationError
                    else -> _exploreAuthState.value = ExploreAuthState.EtcError
                }
            }
        }
    }
}
