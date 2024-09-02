package com.teamoffroad.feature.mypage.presentation.mapper

import com.teamoffroad.feature.mypage.domain.model.Character
import com.teamoffroad.feature.mypage.presentation.model.CharacterModel

fun Character.toUi(): CharacterModel {
    return CharacterModel(
        characterId = characterId,
        characterName = characterName,
        characterThumbnailImageUrl = characterThumbnailImageUrl,
        characterMainColorCode = characterMainColorCode,
        characterSubColorCode = characterSubColorCode,
        isNewGained = isNewGained,
        isGained = isGained,
    )
}