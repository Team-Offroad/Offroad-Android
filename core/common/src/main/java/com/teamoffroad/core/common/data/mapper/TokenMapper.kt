package com.teamoffroad.core.common.data.mapper

import com.teamoffroad.core.common.data.remote.response.TokenResponseDto
import com.teamoffroad.core.common.domain.model.Token

fun TokenResponseDto.toDomain(): Token {
    return Token(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken,
    )
}
