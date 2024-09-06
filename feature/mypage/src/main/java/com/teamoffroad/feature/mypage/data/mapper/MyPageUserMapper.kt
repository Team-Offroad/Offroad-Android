package com.teamoffroad.feature.mypage.data.mapper

import com.teamoffroad.feature.mypage.data.model.MyPageUserEntity
import com.teamoffroad.feature.mypage.data.remote.response.MyPageUserResponseDto
import com.teamoffroad.feature.mypage.domain.model.MyPageUser

fun MyPageUserResponseDto.toData(): MyPageUserEntity {
    return MyPageUserEntity(
        nickname = nickname,
        currentEmblem = currentEmblem,
        elapsedDay = elapsedDay,
        completeQuestCount = completeQuestCount,
        visitedPlaceCount = visitedPlaceCount
    )
}

fun MyPageUserEntity.toDomain(): MyPageUser {
    return MyPageUser(
        nickname = nickname,
        currentEmblem = currentEmblem,
        elapsedDay = elapsedDay,
        completeQuestCount = completeQuestCount,
        visitedPlaceCount = visitedPlaceCount
    )
}