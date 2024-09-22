package com.teamoffroad.feature.auth.data.remote.request

import kotlinx.serialization.Serializable

@Serializable
data class UserMarketingInfoRequestDto(
    val marketing: Boolean
)