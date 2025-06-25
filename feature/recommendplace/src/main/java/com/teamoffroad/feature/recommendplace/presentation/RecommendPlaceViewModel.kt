package com.teamoffroad.feature.recommendplace.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naver.maps.geometry.LatLng
import com.teamoffroad.characterchat.presentation.model.ChatModel
import com.teamoffroad.characterchat.presentation.model.ChatModel.Companion.toTime
import com.teamoffroad.characterchat.presentation.model.ChatType
import com.teamoffroad.feature.explore.domain.usecase.GetMapPlaceListUseCase
import com.teamoffroad.feature.explore.domain.usecase.GetPreviousLocationUseCase
import com.teamoffroad.feature.explore.domain.usecase.SavePreviousLocationUseCase
import com.teamoffroad.feature.explore.presentation.mapper.toUi
import com.teamoffroad.feature.explore.presentation.model.PlaceCategory
import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendationsOrderChatRequest
import com.teamoffroad.feature.recommendplace.domain.repository.PlaceRecommendationsRepository
import com.teamoffroad.feature.recommendplace.presentation.model.PlaceRecommendationsChatUiState
import com.teamoffroad.feature.recommendplace.presentation.model.PlaceRecommendationsFixedPhraseUiState
import com.teamoffroad.feature.recommendplace.presentation.model.PlaceRecommendationsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class RecommendPlaceViewModel @Inject constructor(
    private val getMapPlaceListUseCase: GetMapPlaceListUseCase,
    private val savePreviousLocationUseCase: SavePreviousLocationUseCase,
    private val placeRecommendationsRepository: PlaceRecommendationsRepository,
    private val getPreviousLocationUseCase: GetPreviousLocationUseCase,
) : ViewModel() {
    private val _placeRecommendationsUiState = MutableStateFlow(PlaceRecommendationsUiState())
    val placeRecommendationsUiState = _placeRecommendationsUiState.asStateFlow()

    private val _placeRecommendationsFixedPhraseUiState = MutableStateFlow(PlaceRecommendationsFixedPhraseUiState())
    val placeRecommendationsFixedPhraseUiState = _placeRecommendationsFixedPhraseUiState.asStateFlow()

    private val _placeRecommendationsChatsUiState = MutableStateFlow(PlaceRecommendationsChatUiState())
    val placeRecommendationsChatsUiState = _placeRecommendationsChatsUiState.asStateFlow()

    init {
        initUpdateOrderChats()
    }

    private fun initUpdateOrderChats() {
        updateOrderChats(
            ChatModel(
                text = "반가워 나는 추천 오브 츄링이야!\n장소 추천이 필요해?",
                time = LocalDateTime.now().toString().toTime(),
                chatType = ChatType.ORB_CHARACTER,
                isPlaceRecommendation = false
            )
        )
    }

    fun resetPlaceRecommendationsChats() {
        _placeRecommendationsChatsUiState.value = _placeRecommendationsChatsUiState.value.copy(
            chats = emptyMap()
        )
        initUpdateOrderChats()
    }

    fun getPlaceRecommendationsFixedPhrase() {
        viewModelScope.launch {
            runCatching {
                _placeRecommendationsFixedPhraseUiState.value = _placeRecommendationsFixedPhraseUiState.value.copy(
                    isLoading = true,
                    isError = false
                )
                placeRecommendationsRepository.fetchPlaceRecommendationsFixedPhrase()
            }.onSuccess { phrase ->
                _placeRecommendationsFixedPhraseUiState.value = _placeRecommendationsFixedPhraseUiState.value.copy(
                    content = phrase.content,
                    isLoading = false,
                    isError = false
                )
            }.onFailure {
                _placeRecommendationsFixedPhraseUiState.value = _placeRecommendationsFixedPhraseUiState.value.copy(
                    isLoading = false,
                    isError = true
                )
            }
        }
    }

    fun updateOrderChats(chat: ChatModel) {
        val currentChats = _placeRecommendationsChatsUiState.value.chats.toMutableMap()
        val chatsForDate = currentChats[chat.date]?.toMutableList() ?: mutableListOf()
        chatsForDate.add(chat)
        currentChats[chat.date] = chatsForDate
        _placeRecommendationsChatsUiState.value = _placeRecommendationsChatsUiState.value.copy(
            chats = currentChats,
            isLoading = false,
            isError = false
        )
    }

    fun getPlaceRecommendationsOrderChats(content: String) {
        viewModelScope.launch {
            runCatching {
                _placeRecommendationsChatsUiState.value = _placeRecommendationsChatsUiState.value.copy(
                    isLoading = true,
                    isError = false
                )
                placeRecommendationsRepository.postPlaceRecommendationsOrderChat(PlaceRecommendationsOrderChatRequest(content))
            }.onSuccess { chat ->
                val chatModel = ChatModel(
                    text = chat.content,
                    time = LocalDateTime.now().toString().toTime(),
                    chatType = ChatType.ORB_CHARACTER,
                    isPlaceRecommendation = true
                )

                val updatedChats = _placeRecommendationsChatsUiState.value.chats.toMutableMap()
                val chatsForDate = updatedChats[chatModel.date]?.toMutableList() ?: mutableListOf()
                chatsForDate.add(chatModel)
                updatedChats[chatModel.date] = chatsForDate

                _placeRecommendationsChatsUiState.value = _placeRecommendationsChatsUiState.value.copy(
                    chats = updatedChats,
                    isLoading = false,
                    isError = false
                )
            }.onFailure {
                _placeRecommendationsChatsUiState.value = _placeRecommendationsChatsUiState.value.copy(
                    isLoading = false,
                    isError = true
                )
            }
        }
    }

    fun getPlaceRecommendations() {
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
                    isLoadable = false,
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
                            categoryImageUrl = it.categoryImageUrl,
                            location = LatLng(it.latitude, it.longitude),
                            visitCount = it.visitCount
                        )
                    }
                )
            }.onFailure { t ->
                _placeRecommendationsUiState.value = _placeRecommendationsUiState.value.copy(
                    isLoading = false,
                    isAdditionalLoading = false,
                    isError = true,
                    errorMessage = t.message.toString(),
                    recommendations = emptyList()
                )
            }
        }
    }

    init {
        viewModelScope.launch {
            runCatching {
                getPreviousLocationUseCase()
                    .firstOrNull()
                    ?.let { (latitude, longitude) ->
                        updateLocation(latitude, longitude)
                        updateCameraState(latitude, longitude)
                    } ?: updateLocation(
                    placeRecommendationsUiState.value.locationModel.location.latitude,
                    placeRecommendationsUiState.value.locationModel.location.longitude
                )
            }
        }
    }

    private fun updateCameraState(latitude: Double, longitude: Double) {
        _placeRecommendationsUiState.value = placeRecommendationsUiState.value.copy(
            locationModel = placeRecommendationsUiState.value.locationModel.updateCameraPositionState(
                latitude,
                longitude
            )
        )
    }

    fun updateLocation(latitude: Double, longitude: Double) {
        _placeRecommendationsUiState.value = placeRecommendationsUiState.value.copy(
            locationModel = placeRecommendationsUiState.value.locationModel.updateLocation(
                latitude,
                longitude
            )
        )
        if (placeRecommendationsUiState.value.locationModel.isUserMoveFarEnough() || placeRecommendationsUiState.value.recommendations.isEmpty()) {
            _placeRecommendationsUiState.value = placeRecommendationsUiState.value.copy(
                locationModel = placeRecommendationsUiState.value.locationModel.updatePreviousLocation(
                    LatLng(latitude, longitude)
                ),
            )
            updatePlaces(latitude, longitude)
        }
        viewModelScope.launch {
            savePreviousLocationUseCase(latitude, longitude)
        }
    }

    fun updateTrackingToggle(isUserTrackingEnabled: Boolean) {
        if (!isUserTrackingEnabled) updatePlaces()
        _placeRecommendationsUiState.value = placeRecommendationsUiState.value.copy(
            locationModel = placeRecommendationsUiState.value.locationModel.updateTrackingToggle(
                isUserTrackingEnabled
            )
        )
    }

    fun updatePlaces(
        latitude: Double = placeRecommendationsUiState.value.locationModel.location.latitude,
        longitude: Double = placeRecommendationsUiState.value.locationModel.location.longitude,
    ) {
        viewModelScope.launch {
            runCatching {
                getMapPlaceListUseCase(latitude, longitude, LOAD_PLACES_LIMIT).map { it.toUi() }
            }.onSuccess { places ->
                _placeRecommendationsUiState.value = placeRecommendationsUiState.value.copy(
                    isLoadable = false,
                )
            }.onFailure {
                _placeRecommendationsUiState.value = placeRecommendationsUiState.value.copy(
                    recommendations = emptyList(),
                    isLoadable = false,
                )
            }
        }
    }

    fun updateSelectedPlace(place: PlaceRecommendationsUiState.RecommendationsUiState?) {
        _placeRecommendationsUiState.value = placeRecommendationsUiState.value.copy(
            selectedPlace = place,
        )
    }

    companion object {
        private const val LOAD_PLACES_LIMIT = 100
    }

}