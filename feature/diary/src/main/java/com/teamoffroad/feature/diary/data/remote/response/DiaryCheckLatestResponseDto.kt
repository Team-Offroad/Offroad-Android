package com.teamoffroad.feature.diary.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class DiaryCheckLatestResponseDto(
    val doesNotExistOrChecked: Boolean,
)