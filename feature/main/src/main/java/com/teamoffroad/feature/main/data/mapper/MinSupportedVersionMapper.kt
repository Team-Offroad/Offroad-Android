package com.teamoffroad.feature.main.data.mapper

import com.teamoffroad.feature.home.data.model.DummyUserEntity
import com.teamoffroad.feature.home.data.remote.response.DummyUserResponseDto
import com.teamoffroad.feature.home.domain.model.DummyUser
import com.teamoffroad.feature.main.data.model.MinSupportedVersionEntity
import com.teamoffroad.feature.main.data.remote.response.MinSupportedVersionResponseDto
import com.teamoffroad.feature.main.domain.model.MinSupportedVersion

fun MinSupportedVersionResponseDto.toData(): MinSupportedVersionEntity {
    return MinSupportedVersionEntity(
        ios = ios,
        android = android
    )
}

fun MinSupportedVersionEntity.toDomain(): MinSupportedVersion {
    return MinSupportedVersion(
        ios = ios,
        android = android,
    )
}
