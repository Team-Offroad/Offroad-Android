package com.teamoffroad.feature.auth.data.mapper

import com.teamoffroad.feature.auth.data.remote.response.SignInInfoResponseDto
import com.teamoffroad.feature.auth.domain.model.UserToken

fun SignInInfoResponseDto.toDomain(): UserToken {
    return UserToken(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken,
    )
}
