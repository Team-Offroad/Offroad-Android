package com.teamoffroad.feature.mypage.presentation.mapper

import com.teamoffroad.feature.mypage.domain.model.CharacterDetail
import com.teamoffroad.feature.mypage.presentation.model.CharacterDetailModel

fun CharacterDetail.toUi(): CharacterDetailModel {
    return CharacterDetailModel(
        characterId = characterId,
        characterName = characterName,
        characterBaseImageUrl = characterBaseImageUrl,
        characterIconImageUrl = characterIconImageUrl,
        characterSummaryDescription = characterSummaryDescription,
        characterDescription = characterDescription,
        characterMainColorCode = characterMainColorCode,
        characterSubColorCode = characterSubColorCode,
    )
}
