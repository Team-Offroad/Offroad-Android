package com.teamoffroad.feature.home.presentation.model

sealed class HomeDownloadResult<out T> {
    data class Success<out T>(val data: T) : HomeDownloadResult<T>()
    data class Error(val message: String) : HomeDownloadResult<Nothing>()
}