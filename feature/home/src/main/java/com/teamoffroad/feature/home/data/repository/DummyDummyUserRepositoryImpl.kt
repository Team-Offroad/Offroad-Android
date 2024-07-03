package com.teamoffroad.feature.home.data.repository

import com.teamoffroad.feature.home.data.mapper.toData
import com.teamoffroad.feature.home.data.mapper.toDomain
import com.teamoffroad.feature.home.data.remote.response.DummyUserResponseDto
import com.teamoffroad.feature.home.data.remote.service.DummyUserService
import com.teamoffroad.feature.home.domain.model.DummyUser
import com.teamoffroad.feature.home.domain.repository.DummyUserRepository
import javax.inject.Inject

class DummyDummyUserRepositoryImpl @Inject constructor(
    private val dummyUserService: DummyUserService
) : DummyUserRepository {

    override suspend fun fetchUsers(): List<DummyUser> {
        val dummyUsers = listOf(
            DummyUserResponseDto(1, "유정현", ""),
            DummyUserResponseDto(2, "이석찬", ""),
            DummyUserResponseDto(3, "이근원", ""),
        )
//        return dummyUserService.getUsers().map { it.toData() }.map { it.toDomain() }
        return dummyUsers.map { it.toData() }.map { it.toDomain() }
    }
}
