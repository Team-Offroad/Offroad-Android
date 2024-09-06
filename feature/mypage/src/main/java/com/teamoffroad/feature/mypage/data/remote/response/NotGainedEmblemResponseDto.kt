package com.teamoffroad.feature.mypage.data.remote.response

import com.teamoffroad.feature.mypage.domain.model.GainedEmblems
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotGainedEmblemResponseDto(

    @SerialName("clearConditionQuestName")
    val clearConditionQuestName: String,

    @SerialName("emblemName")
    val emblemName: String,

    @SerialName("isNewGained")
    val isNewGained: Boolean
)