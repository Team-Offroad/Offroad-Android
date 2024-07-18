package com.teamoffroad.feature.home.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsersAdventuresInformationsResponseDto(
    @SerialName("nickname")
    val nickname: String,

    @SerialName("emblemName")
    val emblemName: String,

    @SerialName("baseImageUrl")
    val baseImageUrl: String,

    @SerialName("motionImageUrl")
    val motionImageUrl: String,

    @SerialName("characterName")
    val characterName: String,
)