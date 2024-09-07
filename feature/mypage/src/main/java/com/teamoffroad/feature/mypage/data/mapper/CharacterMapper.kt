package com.teamoffroad.feature.mypage.data.mapper

import com.teamoffroad.feature.mypage.data.remote.response.CharacterDetailResponseDto
import com.teamoffroad.feature.mypage.domain.model.CharacterDetail

fun CharacterDetailResponseDto.toDomain(): CharacterDetail {
    return CharacterDetail(
        characterId = characterId,
        characterName = characterName,
        characterBaseImageUrl = characterBaseImageUrl,
        characterIconImageUrl = characterIconImageUrl,
        characterSummaryDescription = characterSummaryDescription,
        characterDescription = characterDescription,
        characterMainColorCode = characterMainColorCdoe.toLong(radix = 16),
        characterSubColorCode = characterSubColorCode.toLong(radix = 16),
    )
}