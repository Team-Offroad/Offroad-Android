package com.teamoffroad.feature.auth.presentation

sealed interface AuthState {
    data object Empty : AuthState
    data object Editing : AuthState
    data object InvalidateNickname : AuthState
    data object DuplicateNickname : AuthState
}