package com.teamoffroad.feature.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.teamoffroad.feature.home.presentation.model.HomeErrorMessageModel
import com.teamoffroad.feature.main.domain.repository.MinSupportedVersionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val minSupportedVersionRepository: MinSupportedVersionRepository
) : ViewModel() {
    private val _minSupportedVersion = MutableStateFlow("")
    val minSupportedVersion = _minSupportedVersion.asStateFlow()

    private val _mainUiState = MutableStateFlow(MainUiState())
    val mainUiState = _mainUiState.asStateFlow()

    fun getMinSupportedVersion() {
        viewModelScope.launch {
            runCatching {
                minSupportedVersionRepository.fetchMinSupportedVersion()
            }.onSuccess { state ->
                Log.d("orb ttt", state.android)
            }.onFailure { t ->
                val errorMessage = getErrorMessage(t)
                Log.d("orb ttt", "fail $t $errorMessage")
            }
        }
    }

    fun navigateToAnnouncement(announcementId: String) {
        _mainUiState.value = _mainUiState.value.copy(
            announcementId = announcementId
        )
    }

    fun initState() {
        _mainUiState.value = _mainUiState.value.copy(
            announcementId = null,
        )
    }
}

fun getErrorMessage(t: Any): String {
    val gson = Gson()

    return if (t is HttpException) {
        val errorBody = t.response()?.errorBody()?.string()
        errorBody?.let {
            gson.fromJson(it, HomeErrorMessageModel::class.java).message
        } ?: t.message.toString()
    } else t.toString()
}