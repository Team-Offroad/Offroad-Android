package com.teamoffroad.feature.explore.presentation.model

sealed interface ExploreAuthState {
    data object None : ExploreAuthState

    data class LocationError(
        val characterImageUrl: String = "",
    ) : ExploreAuthState

    data class DuplicateError(
        val characterImageUrl: String = "",
    ) : ExploreAuthState

    data object EtcError : ExploreAuthState

    data class Success(
        val category: PlaceCategory = PlaceCategory.NONE,
        val characterImageUrl: String = "",
        val completeQuests: List<String> = emptyList(),
    ) : ExploreAuthState

    companion object {
        fun from(value: String): ExploreAuthState =
            when (value) {
                "None" -> None
                "Success" -> Success()
                "LocationError" -> LocationError()
                "EtcError" -> EtcError
                else -> None
            }
    }
}
