package com.teamoffroad.feature.home.domain.usecase

import com.teamoffroad.feature.home.domain.entity.DummyUserEntity
import com.teamoffroad.feature.home.domain.repository.DummyUserRepository
import javax.inject.Inject

class GetDummyUserListUseCase @Inject constructor(
    private val dummyUserRepository: DummyUserRepository
) {
    suspend operator fun invoke(): List<DummyUserEntity> {
        return dummyUserRepository.fetchUsers()
    }
}
