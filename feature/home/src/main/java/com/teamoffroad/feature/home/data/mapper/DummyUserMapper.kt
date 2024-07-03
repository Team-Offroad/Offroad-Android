package com.teamoffroad.feature.home.data.mapper

import com.teamoffroad.feature.home.data.remote.response.DummyUserResponseDto
import com.teamoffroad.feature.home.domain.entity.DummyUserEntity

fun DummyUserResponseDto.toEntity(): DummyUserEntity {
    return DummyUserEntity(
        id = id,
        name = name,
        email = email
    )
}
