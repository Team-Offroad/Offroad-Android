package com.teamoffroad.feature.auth.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponseDto(
    @SerialName("id")
    val id: Int,
    @SerialName("description")
    val description: String,
    @SerialName("characterBaseImageUrl")
    val characterBaseImageUrl: String,
    @SerialName("name")
    val name: String,
    @SerialName("characterCode")
    val characterCode: String,
)
