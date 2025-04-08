package com.teamoffroad.core.common.data.mapper

import com.teamoffroad.core.common.data.model.MinSupportedVersionEntity
import com.teamoffroad.core.common.data.remote.response.MinSupportedVersionResponseDto
import com.teamoffroad.core.common.domain.model.MinSupportedVersion

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
