package com.teamoffroad.feature.mypage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.mypage.domain.usecase.GetAnnouncementUseCase
import com.teamoffroad.feature.mypage.presentation.model.AnnouncementResult
import com.teamoffroad.feature.mypage.presentation.model.AnnouncementUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnnouncementViewModel @Inject constructor(
    private val getAnnouncementUseCase: GetAnnouncementUseCase,
) : ViewModel() {
    private val _announcementUiState: MutableStateFlow<AnnouncementUiState> =
        MutableStateFlow(AnnouncementUiState())
    val announcementUiState: StateFlow<AnnouncementUiState> = _announcementUiState.asStateFlow()

    fun patchAnnouncement() {
        viewModelScope.launch {
            runCatching {
                getAnnouncementUseCase()
            }.onSuccess { result ->
                val patchAnnouncement = result.getOrNull()?.toImmutableList() ?: persistentListOf()
                _announcementUiState.value = _announcementUiState.value.copy(
                    announcementList = patchAnnouncement,
                    announcementValidateResult = AnnouncementResult.Success,
                )
            }.onFailure {
                _announcementUiState.value = _announcementUiState.value.copy(
                    announcementValidateResult = AnnouncementResult.Error
                )
            }
        }
    }
}