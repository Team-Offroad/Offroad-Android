package com.teamoffroad.feature.mypage.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GainedEmblemsResponseDto(
    @SerialName("gainedEmblems")
    val gainedEmblemResponseDtos: List<GainedEmblemResponseDto>,
    @SerialName("notGainedEmblems")
    val notGainedEmblemResponseDtos: List<NotGainedEmblemResponseDto>
)