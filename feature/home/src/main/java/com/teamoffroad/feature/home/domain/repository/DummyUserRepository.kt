package com.teamoffroad.feature.home.domain.repository

import com.teamoffroad.feature.home.domain.model.DummyUser

interface DummyUserRepository {
    suspend fun fetchUsers(): List<DummyUser>
}
