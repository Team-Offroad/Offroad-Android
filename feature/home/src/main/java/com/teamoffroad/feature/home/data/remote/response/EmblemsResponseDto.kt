package com.teamoffroad.feature.home.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmblemsResponseDto(
    @SerialName("emblems")
    val emblems: List<EmblemResponseDto>,
) {
    @Serializable
    data class EmblemResponseDto(
        @SerialName("emblemCode")
        val emblemCode: String,

        @SerialName("emblemName")
        val emblemName: String
    )
}
