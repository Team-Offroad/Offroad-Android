package com.teamoffroad.feature.mypage.data.mapper

import com.teamoffroad.feature.mypage.data.remote.response.MotionsResponseDto.MotionResponseDto
import com.teamoffroad.feature.mypage.domain.model.CharacterMotion

fun MotionResponseDto.toDomain(isGained: Boolean): CharacterMotion {
    return CharacterMotion(
        category = category,
        characterMotionImageUrl = characterMotionImageUrl,
        isNewGained = isNewGained,
        isGained = isGained,
    )
}
