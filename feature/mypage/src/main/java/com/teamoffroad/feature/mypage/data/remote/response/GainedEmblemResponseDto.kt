@file:JvmName("GainedEmblemsResponseDtoKt")

package com.teamoffroad.feature.mypage.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GainedEmblemResponseDto(
    @SerialName("clearConditionQuestName")
    val clearConditionQuestName: String,

    @SerialName("emblemName")
    val emblemName: String,

    @SerialName("isNewGained")
    val isNewGained: Boolean,
)
