package com.teamoffroad.feature.home.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserQuestsResponseDto(
    @SerialName("recent")
    val recentResponseDto: RecentResponseDto,

    @SerialName("almost")
    val almostResponseDto: AlmostResponseDto
){
    @Serializable
    data class RecentResponseDto(
        @SerialName("questName")
        val questName: String,

        @SerialName("progress")
        val progress: Int,

        @SerialName("completeCondition")
        val completeCondition: Int
    )

    @Serializable
    data class AlmostResponseDto(
        @SerialName("questName")
        val questName: String,

        @SerialName("progress")
        val progress: Int,

        @SerialName("completeCondition")
        val completeCondition: Int
    )
}
