package com.teamoffroad.feature.mypage.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GainedEmblemsResponseDto(
    @SerialName("gainedEmblems")
    val gainedEmblems: List<GainedEmblem>,
    @SerialName("notGainedEmblems")
    val notGainedEmblems: List<NotGainedEmblem>
)