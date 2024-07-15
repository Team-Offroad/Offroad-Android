package com.teamoffroad.feature.home.domain.model

data class Emblems(
    val emblems: List<Emblem>
) {
    data class Emblem(
        val emblemCode: String,
        val emblemName: String
    )
}