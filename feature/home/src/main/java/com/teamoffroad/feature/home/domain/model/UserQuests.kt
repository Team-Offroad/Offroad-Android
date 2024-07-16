package com.teamoffroad.feature.home.domain.model

data class UserQuests(
    val userRecent: UserRecent?,
    val userAlmost: UserAlmost?
) {
    data class UserRecent(
        val questName: String,
        val progress: Int,
        val completeCondition: Int
    )

    data class UserAlmost(
        val questName: String,
        val progress: Int,
        val completeCondition: Int
    )
}
