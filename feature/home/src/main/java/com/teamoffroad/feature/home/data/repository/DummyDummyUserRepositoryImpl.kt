package com.teamoffroad.feature.home.data.repository

import com.teamoffroad.feature.home.data.mapper.toDomain
import com.teamoffroad.feature.home.data.remote.service.DummyUserService
import com.teamoffroad.feature.home.domain.entity.DummyUserEntity
import com.teamoffroad.feature.home.domain.repository.DummyUserRepository
import javax.inject.Inject

class DummyDummyUserRepositoryImpl @Inject constructor(
    private val dummyUserService: DummyUserService
) : DummyUserRepository {

    override suspend fun getUsers(): List<DummyUserEntity> {
        val userResponses = dummyUserService.getUsers()
        return userResponses.map { it.toDomain() }
    }
}
