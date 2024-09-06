package com.teamoffroad.feature.explore.presentation.model

sealed interface ExploreAuthState {
    data object None : ExploreAuthState
    data object Loading : ExploreAuthState
    data object LocationError : ExploreAuthState
    data object CodeError : ExploreAuthState
    data object EtcError : ExploreAuthState
    data class Success(
        val category: PlaceCategory = PlaceCategory.NONE,
        val characterImageUrl: String = "",
        val completeQuests: List<String> = emptyList(),
    ) : ExploreAuthState

    companion object {
        fun from(value: String): ExploreAuthState {
            return when (value) {
                "None" -> None
                "Loading" -> Loading
                "Success" -> Success()
                "LocationError" -> LocationError
                "CodeError" -> CodeError
                "EtcError" -> EtcError
                else -> None
            }
        }
    }
}
