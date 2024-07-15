package com.teamoffroad.feature.home.data.model

data class EmblemsEntity(
    val emblems: List<EmblemEntity>
) {
    data class EmblemEntity(
        val emblemCode: String,
        val emblemName: String
    )
}