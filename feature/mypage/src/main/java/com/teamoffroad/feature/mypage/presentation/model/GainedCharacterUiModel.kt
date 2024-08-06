package com.teamoffroad.feature.mypage.presentation.model


data class CharacterModel(
    val characterName: String,
    val characterThumbnailImageUrl: String,
    val characterIconImageUrl: String,
    val characterDescription: String,
    val characterTitle: String,
    val characterBackgroundColor: Long,
    val characterFrameColor: Long,
) {
    companion object {
        val dummyCharacter = CharacterModel(
            characterName = "루미",
            characterThumbnailImageUrl = "https://github.com/user-attachments/assets/bbad497c-ee1e-4bf0-91e7-a00bf9480263",
            characterIconImageUrl = "https://github.com/user-attachments/assets/8ca8e7a6-2e9d-4501-ac50-04636259eeee",
            characterDescription = "재밌는 걸 보면 두 눈이 반짝!\n호기심이 많은 탐험가 루미예요.\n파우치에는 최소한의 탐험 키트가 들어있답니다.",
            characterTitle = "호기심이 많은 탐험가",
            characterBackgroundColor = 0xFFFFF0BC,
            characterFrameColor = 0xFFFED761
        )
    }
}