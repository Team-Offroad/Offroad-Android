package com.teamoffroad.feature.auth.data.remote.request

import kotlinx.serialization.SerialName

data class DuplicateNicknameRequestDto (
    @SerialName("nickname")
    val nickname: String,
)