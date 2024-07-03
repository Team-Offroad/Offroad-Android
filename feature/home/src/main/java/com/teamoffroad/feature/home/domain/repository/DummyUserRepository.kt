package com.teamoffroad.feature.home.domain.repository

import com.teamoffroad.feature.home.domain.entity.DummyUserEntity

interface DummyUserRepository {
    suspend fun getUsers(): List<DummyUserEntity>
}
