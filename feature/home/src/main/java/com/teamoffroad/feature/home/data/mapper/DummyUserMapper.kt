package com.teamoffroad.feature.home.data.mapper

import com.teamoffroad.feature.home.data.model.DummyUserEntity
import com.teamoffroad.feature.home.data.remote.response.DummyUserResponseDto
import com.teamoffroad.feature.home.domain.model.DummyUser

fun DummyUserResponseDto.toData(): DummyUserEntity{
    return DummyUserEntity(
        id = id,
        name = name,
        email = email,
    )
}

fun DummyUserEntity.toDomain(): DummyUser{
    return DummyUser(
        id = id,
        name = name,
        email = email,
    )
}
