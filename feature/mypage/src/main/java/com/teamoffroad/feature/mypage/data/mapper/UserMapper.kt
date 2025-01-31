package com.teamoffroad.feature.mypage.data.mapper

import com.teamoffroad.feature.mypage.data.model.MyPageUserEntity
import com.teamoffroad.feature.mypage.data.remote.response.CharacterResponseDto
import com.teamoffroad.feature.mypage.data.remote.response.MyPageUserResponseDto
import com.teamoffroad.feature.mypage.domain.model.Character
import com.teamoffroad.feature.mypage.domain.model.MyPageUser

fun CharacterResponseDto.toDomain(isGained: Boolean, isRepresentative: Boolean = false): Character {
    return Character(
        characterId = characterId,
        characterName = characterName,
        characterThumbnailImageUrl = characterThumbnailImageUrl,
        characterMainColorCode = characterMainColorCode.toLongWithAlpha(),
        characterSubColorCode = characterSubColorCode.toLongWithAlpha(),
        isRepresentative = isRepresentative,
        isNewGained = isNewGained,
        isGained = isGained,
    )
}

fun MyPageUserResponseDto.toData(): MyPageUserEntity {
    return MyPageUserEntity(
        nickname = nickname,
        currentEmblem = currentEmblem,
        elapsedDay = elapsedDay,
        completeQuestCount = completeQuestCount,
        visitedPlaceCount = visitedPlaceCount,
        characterImageUrl = characterImageUrl
    )
}

fun MyPageUserEntity.toDomain(): MyPageUser {
    return MyPageUser(
        nickname = nickname,
        currentEmblem = currentEmblem,
        elapsedDay = elapsedDay,
        completeQuestCount = completeQuestCount,
        visitedPlaceCount = visitedPlaceCount,
        characterImageUrl = characterImageUrl
    )
}
