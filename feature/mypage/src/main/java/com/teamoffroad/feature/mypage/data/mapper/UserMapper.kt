package com.teamoffroad.feature.mypage.data.mapper

import com.teamoffroad.feature.mypage.data.remote.response.CharacterResponseDto
import com.teamoffroad.feature.mypage.domain.model.Character

fun CharacterResponseDto.toDomain(isGained: Boolean, isRepresentative: Boolean = false): Character {
    return Character(
        characterId = characterId,
        characterName = characterName,
        characterThumbnailImageUrl = characterThumbnailImageUrl,
        characterMainColorCode = characterMainColorCode.toLong(radix = 16),
        characterSubColorCode = characterSubColorCode.toLong(radix = 16),
        isRepresentative = isRepresentative,
        isNewGained = isNewGained,
        isGained = isGained,
    )
}