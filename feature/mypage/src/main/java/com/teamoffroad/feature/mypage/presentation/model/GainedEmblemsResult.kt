package com.teamoffroad.feature.mypage.presentation.model

interface GainedEmblemsResult {
    data object Empty: GainedEmblemsResult
    data object Error: GainedEmblemsResult
    data object Success: GainedEmblemsResult
}