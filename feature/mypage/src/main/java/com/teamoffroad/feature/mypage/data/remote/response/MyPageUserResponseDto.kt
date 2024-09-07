package com.teamoffroad.feature.mypage.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyPageUserResponseDto(
    @SerialName("nickname")
    val nickname: String,

    @SerialName("currentEmblem")
    val currentEmblem: String,

    @SerialName("elapsedDay")
    val elapsedDay: Int,

    @SerialName("completeQuestCount")
    val completeQuestCount: Int,

    @SerialName("visitedPlaceCount")
    val visitedPlaceCount: Int,

    )
