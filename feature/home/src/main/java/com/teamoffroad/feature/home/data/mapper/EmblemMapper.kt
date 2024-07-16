package com.teamoffroad.feature.home.data.mapper

import com.teamoffroad.feature.home.data.model.EmblemEntity
import com.teamoffroad.feature.home.data.remote.response.emblem.EmblemsResponseDto
import com.teamoffroad.feature.home.domain.model.Emblem

fun EmblemsResponseDto.EmblemResponseDto.toData(): EmblemEntity {
    return EmblemEntity(
        emblemCode = emblemCode,
        emblemName = emblemName
    )
}

fun EmblemEntity.toDomain(): Emblem {
    return Emblem(
        emblemCode = emblemCode,
        emblemName = emblemName
    )
}