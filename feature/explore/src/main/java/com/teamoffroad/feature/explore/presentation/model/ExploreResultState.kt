package com.teamoffroad.feature.explore.presentation.model

sealed interface ExploreResultState {
    data object None : ExploreResultState
    data object Loading : ExploreResultState
    data object Success : ExploreResultState
    data object LocationError : ExploreResultState
    data object CodeError : ExploreResultState
    data object EtcError : ExploreResultState
}
