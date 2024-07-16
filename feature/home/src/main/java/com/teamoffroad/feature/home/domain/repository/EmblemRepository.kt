package com.teamoffroad.feature.home.domain.repository

import com.teamoffroad.feature.home.domain.model.Emblem
import com.teamoffroad.feature.home.domain.model.UserQuests

interface EmblemRepository {
    suspend fun getEmblems(): List<Emblem>

    suspend fun patchEmblem(emblemCode: String)

    suspend fun getUserQuests(): UserQuests
}