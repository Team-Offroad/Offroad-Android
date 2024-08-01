package com.teamoffroad.feature.explore.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.explore.domain.usecase.PostExploreQrAuthUseCase
import com.teamoffroad.feature.explore.presentation.model.ExploreResultState
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

    private val _exploreResultState: MutableStateFlow<ExploreResultState> = MutableStateFlow(ExploreResultState.None)
    val exploreResultState: StateFlow<ExploreResultState> = _exploreResultState.asStateFlow()

    private val _resultImageUrl: MutableStateFlow<String> = MutableStateFlow("")
    val resultImageUrl: StateFlow<String> = _resultImageUrl.asStateFlow()

    fun postExploreResult(placeId: Long, latitude: Double, longitude: Double, qr: String) {
        if (exploreResultState.value is ExploreResultState.Loading) return
        _exploreResultState.value = ExploreResultState.Loading

        viewModelScope.launch {
            runCatching {
                postExploreQrAuthUseCase.invoke(placeId, qr, latitude, longitude)
            }.onSuccess { exploreResult ->
                when (exploreResult.isQRMatched) {
                    true -> {
                        _resultImageUrl.value = exploreResult.characterImageUrl
                        _exploreResultState.value = ExploreResultState.Success
                    }

                    false -> {
                        _resultImageUrl.value = exploreResult.characterImageUrl
                        _exploreResultState.value = ExploreResultState.CodeError
                    }
                }
            }.onFailure {
                when (it.message) {
                    "HTTP 400" -> _exploreResultState.value = ExploreResultState.LocationError
                    else -> _exploreResultState.value = ExploreResultState.EtcError
                }
            }
        }
    }
}
