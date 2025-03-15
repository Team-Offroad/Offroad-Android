package com.teamoffroad.feature.mypage.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class DiaryCheckLatestResponseDto(
    val doesNotExistOrChecked: Boolean,
)