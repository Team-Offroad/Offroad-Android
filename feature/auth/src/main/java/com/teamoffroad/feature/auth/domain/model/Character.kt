package com.teamoffroad.feature.auth.domain.model

data class Character(
    val id: Int,
    val description: String,
    val characterBaseImageUrl: String,
    val name: String,
    val characterCode: String,
)
