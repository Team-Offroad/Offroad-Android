package com.teamoffroad.core.common.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val message: String,
    val data: T?,
)