package com.teamoffroad.feature.auth.data.remote.request

import kotlinx.serialization.SerialName

data class ProfileUpdateRequestDto(
    @SerialName("nickname")
    val nickname: String,

    @SerialName("year")
    val year: String,

    @SerialName("month")
    val month: String,

    @SerialName("day")
    val day: String,

    @SerialName("gender")
    val gender: String,
)