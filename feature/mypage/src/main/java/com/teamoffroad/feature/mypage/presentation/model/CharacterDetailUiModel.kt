package com.teamoffroad.feature.mypage.presentation.model

data class CharacterDetailModel(
    val characterId: Int = 0,
    val characterName: String = "",
    val characterBaseImageUrl: String = "",
    val characterIconImageUrl: String = "",
    val characterSummaryDescription: String = "",
    val characterDescription: String = "",
    val characterMainColorCode: Long = 0,
    val characterSubColorCode: Long = 0,
)

data class CharacterMotionModel(
    val category: CharacterCategory = CharacterCategory.NONE,
    val characterMotionImageUrl: String = "",
    val motionName: String = category.krLabel,
    val isNewGained: Boolean = false,
)
