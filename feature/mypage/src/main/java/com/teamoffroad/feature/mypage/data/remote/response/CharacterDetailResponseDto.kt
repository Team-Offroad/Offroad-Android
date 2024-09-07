package com.teamoffroad.feature.mypage.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDetailResponseDto(
    @SerialName("characterId")
    val characterId: Int,
    @SerialName("characterName")
    val characterName: String,
    @SerialName("characterBaseImageUrl")
    val characterBaseImageUrl: String,
    @SerialName("characterIconImageUrl")
    val characterIconImageUrl: String,
    @SerialName("characterSummaryDescription")
    val characterSummaryDescription: String,
    @SerialName("characterDescription")
    val characterDescription: String,
    @SerialName("characterMainColorCode")
    val characterMainColorCdoe: String,
    @SerialName("characterSubColorCode")
    val characterSubColorCode: String,
)
