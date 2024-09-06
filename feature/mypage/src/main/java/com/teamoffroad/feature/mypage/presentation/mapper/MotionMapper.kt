package com.teamoffroad.feature.mypage.presentation.mapper

import com.teamoffroad.feature.mypage.domain.model.CharacterMotion
import com.teamoffroad.feature.mypage.presentation.model.CharacterCategory
import com.teamoffroad.feature.mypage.presentation.model.CharacterMotionModel

fun CharacterMotion.toUi(): CharacterMotionModel {
    return CharacterMotionModel(
        category = CharacterCategory.from(category),
        characterMotionImageUrl = characterMotionImageUrl,
        isNewGained = isNewGained,
        isGained = isGained,
    )
}
