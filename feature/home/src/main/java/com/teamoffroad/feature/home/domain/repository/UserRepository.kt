package com.teamoffroad.feature.home.domain.repository

import com.teamoffroad.feature.home.domain.model.Emblem
import com.teamoffroad.feature.home.domain.model.UserQuests
import com.teamoffroad.feature.home.domain.model.UsersAdventuresInformation

interface UserRepository {
    suspend fun getEmblems(): List<Emblem>

    suspend fun patchEmblem(emblemCode: String)

    suspend fun getUserQuests(): UserQuests

    suspend fun getUsersAdventuresInformation(category: String): UsersAdventuresInformation
}