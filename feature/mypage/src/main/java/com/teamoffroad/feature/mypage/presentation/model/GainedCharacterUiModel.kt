package com.teamoffroad.feature.mypage.presentation.model

data class CharacterModel(
    val characterId: Int = 0,
    val characterName: String = "",
    val characterThumbnailImageUrl: String = "",
    val characterMainColorCode: Long = 0,
    val characterFrameColorCode: Long = 0,
    val characterSubColorCode: Long = 0,
    val isNewGained: Boolean = false,
    val isGained: Boolean = false,
)
