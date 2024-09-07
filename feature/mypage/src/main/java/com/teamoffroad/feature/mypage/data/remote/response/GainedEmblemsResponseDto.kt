package com.teamoffroad.feature.mypage.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GainedEmblemsResponseDto(
    @SerialName("gainedEmblems")
    val gainedEmblemResponseDto: List<GainedEmblemResponseDto>,
    @SerialName("notGainedEmblems")
    val notGainedEmblemResponseDto: List<NotGainedEmblemResponseDto>,
)