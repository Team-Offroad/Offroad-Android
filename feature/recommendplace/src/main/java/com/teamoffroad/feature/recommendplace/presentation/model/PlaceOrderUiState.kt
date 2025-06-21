package com.teamoffroad.feature.recommendplace.presentation.model

data class PlaceOrderUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val content: String = "",
)
