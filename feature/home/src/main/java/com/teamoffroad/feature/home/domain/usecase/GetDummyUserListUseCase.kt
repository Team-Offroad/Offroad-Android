package com.teamoffroad.feature.home.domain.usecase

import com.teamoffroad.feature.home.domain.model.DummyUser
import com.teamoffroad.feature.home.domain.repository.DummyUserRepository

class GetDummyUserListUseCase(
    private val dummyUserRepository: DummyUserRepository,
) {
    suspend operator fun invoke(): List<DummyUser> {
        return dummyUserRepository.fetchUsers()
    }
}
