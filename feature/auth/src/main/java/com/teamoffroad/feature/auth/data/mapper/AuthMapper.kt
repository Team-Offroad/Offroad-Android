package com.teamoffroad.feature.auth.data.mapper

import com.teamoffroad.feature.auth.data.remote.request.ProfileUpdateRequestDto
import com.teamoffroad.feature.auth.data.remote.response.SignInInfoResponseDto
import com.teamoffroad.feature.auth.domain.model.UserProfile
import com.teamoffroad.feature.auth.domain.model.UserToken

fun SignInInfoResponseDto.toDomain(): UserToken {
    return UserToken(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken,
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
