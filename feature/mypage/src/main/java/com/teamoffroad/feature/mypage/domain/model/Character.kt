package com.teamoffroad.feature.mypage.domain.model

data class Character(
    val characterId: Int,
    val characterName: String,
    val characterThumbnailImageUrl: String,
    val characterMainColorCode: Long,
    val characterSubColorCode: Long,
    val isNewGained: Boolean,
    val isGained: Boolean,
)
