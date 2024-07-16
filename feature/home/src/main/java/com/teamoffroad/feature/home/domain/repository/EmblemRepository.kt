package com.teamoffroad.feature.home.domain.repository

import com.teamoffroad.feature.home.domain.model.Emblem
import com.teamoffroad.feature.home.domain.model.UserQuests

interface EmblemRepository {
    suspend fun getEmblems(token: String): List<Emblem>

    suspend fun getUserQuests(token: String): UserQuests
}