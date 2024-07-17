package com.teamoffroad.feature.home.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsersAdventuresInformationsResponseDto(
    @SerialName("nickname")
    val nickname: String,

    @SerialName("emblemName")
    val emblemName: String,

    @SerialName("characterImgUrl")
    val characterImgUrl: String,

    @SerialName("characterName")
    val characterName: String,
)