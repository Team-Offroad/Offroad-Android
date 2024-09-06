package com.teamoffroad.feature.mypage.data.remote.request

import kotlinx.serialization.Serializable

@Serializable
data class DeleteUserInfoRequestDto(
    val deleteCode: String,
)
