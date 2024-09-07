package com.teamoffroad.feature.mypage.domain.model

data class CharacterDetail(
    val characterId: Int = 0,
    val characterName: String = "",
    val characterBaseImageUrl: String = "",
    val characterIconImageUrl: String = "",
    val characterSummaryDescription: String = "",
    val characterDescription: String = "",
    val characterMainColorCode: Long = 0,
    val characterSubColorCode: Long = 0,
)
