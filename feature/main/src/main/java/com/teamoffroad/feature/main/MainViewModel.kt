package com.teamoffroad.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.teamoffroad.core.common.domain.repository.MinSupportedVersionRepository
import com.teamoffroad.feature.home.presentation.model.HomeErrorMessageModel
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
    private val _appVersionState = MutableStateFlow(true)
    val appVersionState = _appVersionState.asStateFlow()

    private val _mainUiState = MutableStateFlow(MainUiState())
    val mainUiState = _mainUiState.asStateFlow()

    fun getMinSupportedVersion(currentVersion: String) {
        viewModelScope.launch {
            runCatching {
                minSupportedVersionRepository.fetchMinSupportedVersion()
            }.onSuccess { state ->
                _appVersionState.value = compareVersions(currentVersion, state.android)
            }.onFailure { t ->
                val errorMessage = getErrorMessage(t)
            }
        }
    }

    private fun compareVersions(currentVersion: String, minAppVersion: String): Boolean {
        val currentVersionParts = extractNumbers(currentVersion)
        val minAppVersionParts = extractNumbers(minAppVersion)

        for (i in 0 until maxOf(currentVersionParts.size, minAppVersionParts.size)) {
            if (currentVersionParts.getOrElse(i) { 0 } < minAppVersionParts.getOrElse(i) { 0 }) return false
        }
        return true
    }

    private fun extractNumbers(version: String): List<Int> {
        return "\\d+".toRegex().findAll(version)
            .map { it.value.toInt() }
            .toList()
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