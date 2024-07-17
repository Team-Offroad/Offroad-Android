package com.teamoffroad.feature.auth.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponseDto(
    @SerialName("characters")
    val characters: List<CharacterResponseDto>,
)
