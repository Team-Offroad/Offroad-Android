package com.teamoffroad.feature.auth.presentation.model

data class AgreeTermsAndConditionsUiState(
    val serviceUtil: Boolean = false,
    val personalInfo: Boolean = false,
    val location: Boolean = false,
    val marketing: Boolean = false,
    val success: Boolean = false,
)

