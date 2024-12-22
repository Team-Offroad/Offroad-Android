package com.teamoffroad.feature.auth.presentation.termandcondition

data class AgreeTermsAndConditionsUiState(
    val isServiceUtil: Boolean = false,
    val isPersonalInfo: Boolean = false,
    val isLocation: Boolean = false,
    val isMarketing: Boolean = false,
    val success: Boolean = false,
)

