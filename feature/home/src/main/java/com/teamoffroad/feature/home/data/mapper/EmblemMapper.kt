package com.teamoffroad.feature.home.data.mapper

import com.teamoffroad.feature.home.data.model.EmblemsEntity
import com.teamoffroad.feature.home.data.remote.response.emblem.EmblemsResponseDto
import com.teamoffroad.feature.home.domain.model.Emblems

fun EmblemsResponseDto.EmblemResponseDto.toData(): EmblemsEntity.EmblemEntity {
    return EmblemsEntity.EmblemEntity(
        emblemCode = emblemCode,
        emblemName = emblemName
    )
}

fun EmblemsEntity.EmblemEntity.toDomain(): Emblems.Emblem {
    return Emblems.Emblem(
        emblemCode = emblemCode,
        emblemName = emblemName
    )
}