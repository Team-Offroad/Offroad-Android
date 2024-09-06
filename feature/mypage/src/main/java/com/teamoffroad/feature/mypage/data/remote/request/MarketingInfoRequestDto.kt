package com.teamoffroad.feature.mypage.data.remote.request

import kotlinx.serialization.Serializable

@Serializable
data class MarketingInfoRequestDto(
    val marketing: Boolean
)