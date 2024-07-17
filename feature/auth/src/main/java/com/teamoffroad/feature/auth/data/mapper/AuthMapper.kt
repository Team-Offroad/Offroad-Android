package com.teamoffroad.feature.auth.data.mapper

import com.teamoffroad.feature.auth.data.remote.request.ProfileUpdateRequestDto
import com.teamoffroad.feature.auth.data.remote.response.CharacterResponseDto
import com.teamoffroad.feature.auth.data.remote.response.SignInInfoResponseDto
import com.teamoffroad.feature.auth.domain.model.Character
import com.teamoffroad.feature.auth.domain.model.SignInInfo
import com.teamoffroad.feature.auth.domain.model.UserProfile
import com.teamoffroad.feature.auth.domain.model.UserToken

fun SignInInfoResponseDto.toDomain(): SignInInfo {
    return SignInInfo(
        tokens = UserToken(
            accessToken = this.tokens.accessToken,
            refreshToken = this.tokens.refreshToken
        ),
        isAlreadyExist = this.isAlreadyExist
    )
}

fun CharacterResponseDto.toDomain(): Character {
    return Character(
        id = this.id,
        description = this.description,
        characterBaseImageUrl = this.characterBaseImageUrl,
        name = this.name,
        characterCode = this.characterCode,
    )
}

fun mapUserProfileToUpdateRequestDto(userProfile: UserProfile): ProfileUpdateRequestDto {
    return ProfileUpdateRequestDto(
        nickname = userProfile.nickname,
        year = userProfile.year.toString(),
        month = userProfile.month.toString(),
        day = userProfile.day.toString(),
        gender = userProfile.gender
    )
}
