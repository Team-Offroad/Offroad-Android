package com.teamoffroad.feature.mypage.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponseDto(
    @SerialName("gainedCharacters")
    val gainedCharacters: List<CharacterResponseDto>,
    @SerialName("notGainedCharacters")
    val notGainedCharacters: List<CharacterResponseDto>,
    @SerialName("representativeCharacterId")
    val representativeCharacterId: Int,
)
