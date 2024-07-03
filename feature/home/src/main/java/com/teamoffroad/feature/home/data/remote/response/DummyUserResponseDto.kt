package com.teamoffroad.feature.home.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class DummyUserResponseDto(
    val id: Int,
    val name: String,
    val email: String
)
