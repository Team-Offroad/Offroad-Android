package com.teamoffroad.feature.diary.data.mapper

import com.teamoffroad.feature.diary.data.remote.response.HexCodeDto
import com.teamoffroad.feature.diary.domain.model.HexCode

fun HexCodeDto.toHexCode() =
    HexCode(
        small = small,
        large = large,
    )

fun Map<String, List<HexCodeDto>>.toHexCodeMap() =
    this.mapValues { items ->
        items.value.map { it.toHexCode() }
    }