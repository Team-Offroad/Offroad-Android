package com.teamoffroad.feature.mypage.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MotionsResponseDto(
    @SerialName("gainedCharacterMotions")
    val gainedCharacterMotions: List<MotionResponseDto>,
    @SerialName("notGainedCharacterMotions")
    val notGainedCharacterMotions: List<MotionResponseDto>,
) {
    @Serializable
    data class MotionResponseDto(
        @SerialName("category")
        val category: String,
        @SerialName("characterMotionImageUrl")
        val characterMotionImageUrl: String,
        @SerialName("isNewGained")
        val isNewGained: Boolean,
    )
}
