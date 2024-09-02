package com.teamoffroad.feature.mypage.data.mapper

import com.teamoffroad.feature.mypage.data.remote.response.CharacterResponseDto
import com.teamoffroad.feature.mypage.domain.model.Character

fun CharacterResponseDto.toDomain(isGained: Boolean): Character {
    return Character(
        characterId = characterId,
        characterName = characterName,
        characterThumbnailImageUrl = characterThumbnailImageUrl,
        characterMainColorCode = characterMainColorCode,
        characterSubColorCode = characterSubColorCode,
        isNewGained = isNewGained,
        isGained = isGained,
    )
}
