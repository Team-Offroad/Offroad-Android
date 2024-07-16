package com.teamoffroad.feature.home.presentation.model

sealed class DownloadResult<out T> {
    data class Success<out T>(val data: T) : DownloadResult<T>()
    data class Error(val message: String) : DownloadResult<Nothing>()
}