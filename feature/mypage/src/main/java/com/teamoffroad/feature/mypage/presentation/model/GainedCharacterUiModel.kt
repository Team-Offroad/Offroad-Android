package com.teamoffroad.feature.mypage.presentation.model

data class CharacterModel(
    val characterThumbnailModel: CharacterThumbnailModel,
) {
    data class CharacterThumbnailModel(
        val characterId: Int = -1,
        val characterName: String = "",
        val characterThumbnailImageUrl: String = "",
        val characterMainColorCode: Long = -1,
        val characterFrameColorCode: Long = -1,
    ) {
        companion object {
            private val dummyCharacter = CharacterThumbnailModel(
                characterId = 0,
                characterName = "루미",
                characterThumbnailImageUrl = "https://github.com/user-attachments/assets/bbad497c-ee1e-4bf0-91e7-a00bf9480263",
                characterMainColorCode = 0xFFFFF0BC,
                characterFrameColorCode = 0xFFFED761,
            )
            val dummyCharacters = List(11) { dummyCharacter }
        }
    }

    data class CharacterMotionModel(
        val category: String = "",
        val characterMotionImageUrl: String = "",
        val isGained: Boolean = false,
    )
}
