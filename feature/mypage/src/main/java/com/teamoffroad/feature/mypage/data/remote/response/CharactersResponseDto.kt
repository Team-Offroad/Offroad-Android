package com.teamoffroad.feature.mypage.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponseDto(
    @SerialName("isGainedCharacters")
    val isGainedCharacters: List<CharacterResponseDto>,
    @SerialName("isNotGainedCharacters")
    val isNotGainedCharacters: List<CharacterResponseDto>,
)
