package com.teamoffroad.feature.mypage.domain.model

data class Character(
    val characterId: Int,
    val characterName: String,
    val characterThumbnailImageUrl: String,
    val characterMainColorCode: String,
    val characterSubColorCode: String,
    val isNewGained: Boolean,
    val isGained: Boolean,
)
