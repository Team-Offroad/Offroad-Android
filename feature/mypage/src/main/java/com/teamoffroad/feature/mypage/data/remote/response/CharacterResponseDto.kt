package com.teamoffroad.feature.mypage.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponseDto(
    @SerialName("characterId")
    val characterId: Int,
    @SerialName("characterName")
    val characterName: String,
    @SerialName("characterThumbnailImageUrl")
    val characterThumbnailImageUrl: String,
    @SerialName("characterMainColorCode")
    val characterMainColorCode: String,
    @SerialName("characterSubColorCode")
    val characterSubColorCode: String,
    @SerialName("isNewGained")
    val isNewGained: Boolean,
)
